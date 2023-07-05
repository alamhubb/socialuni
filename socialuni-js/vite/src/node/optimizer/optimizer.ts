import colors from 'picocolors'
import { createDebugger, getHash } from '../utils'
import { getDepOptimizationConfig } from '../config'
import type { ResolvedConfig, ViteDevServer } from '..'
import {
  addManuallyIncludedOptimizeDeps,
  addOptimizedDepInfo,
  createIsOptimizedDepFile,
  createIsOptimizedDepUrl,
  depsFromOptimizedDepInfo,
  depsLogString,
  discoverProjectDependencies,
  extractExportsData,
  getOptimizedDepPath,
  initDepsOptimizerMetadata,
  loadCachedDepOptimizationMetadata,
  newDepOptimizationProcessing,
  optimizeServerSsrDeps,
  runOptimizeDeps,
  toDiscoveredDependencies,
} from '.'
import type {
  DepOptimizationProcessing,
  DepOptimizationResult,
  DepsOptimizer,
  OptimizedDepInfo,
} from '.'

const debug = createDebugger('vite:deps')

/**
 * The amount to wait for requests to register newly found dependencies before triggering
 * a re-bundle + page reload
 */
const debounceMs = 100

const depsOptimizerMap = new WeakMap<ResolvedConfig, DepsOptimizer>()
const devSsrDepsOptimizerMap = new WeakMap<ResolvedConfig, DepsOptimizer>()

export function getDepsOptimizer(
  config: ResolvedConfig,
  ssr?: boolean,
): DepsOptimizer | undefined {
  // Workers compilation shares the DepsOptimizer from the main build
  const isDevSsr = ssr && config.command !== 'build'
  return (isDevSsr ? devSsrDepsOptimizerMap : depsOptimizerMap).get(
    config.mainConfig || config,
  )
}

export async function initDepsOptimizer(
  config: ResolvedConfig,
  server?: ViteDevServer,
): Promise<void> {
  // Non Dev SSR Optimizer
  const ssr = config.command === 'build' && !!config.build.ssr
  if (!getDepsOptimizer(config, ssr)) {
    await createDepsOptimizer(config, server)
  }
}

let creatingDevSsrOptimizer: Promise<void> | undefined
export async function initDevSsrDepsOptimizer(
  config: ResolvedConfig,
  server: ViteDevServer,
): Promise<void> {
  if (getDepsOptimizer(config, true)) {
    // ssr
    return
  }
  if (creatingDevSsrOptimizer) {
    return creatingDevSsrOptimizer
  }
  creatingDevSsrOptimizer = (async function () {
    // Important: scanning needs to be done before starting the SSR dev optimizer
    // If ssrLoadModule is called before server.listen(), the main deps optimizer
    // will not be yet created
    const ssr = false
    if (!getDepsOptimizer(config, ssr)) {
      await initDepsOptimizer(config, server)
    }
    await getDepsOptimizer(config, ssr)!.scanProcessing

    await createDevSsrDepsOptimizer(config)
    creatingDevSsrOptimizer = undefined
  })()
  return await creatingDevSsrOptimizer
}

async function createDepsOptimizer(
  config: ResolvedConfig,
  server?: ViteDevServer,
): Promise<void> {
  const { logger } = config
  const isBuild = config.command === 'build'
  const ssr = isBuild && !!config.build.ssr // safe as Dev SSR don't use this optimizer

  const sessionTimestamp = Date.now().toString()

  const cachedMetadata = await loadCachedDepOptimizationMetadata(config, ssr)

  let debounceProcessingHandle: NodeJS.Timeout | undefined

  let closed = false

  let metadata =
    cachedMetadata || initDepsOptimizerMetadata(config, ssr, sessionTimestamp)

  const depsOptimizer: DepsOptimizer = {
    metadata,
    registerMissingImport,
    run: () => debouncedProcessing(0),
    isOptimizedDepFile: createIsOptimizedDepFile(config),
    isOptimizedDepUrl: createIsOptimizedDepUrl(config),
    getOptimizedDepId: (depInfo: OptimizedDepInfo) =>
      isBuild ? depInfo.file : `${depInfo.file}?v=${depInfo.browserHash}`,
    registerWorkersSource,
    delayDepsOptimizerUntil,
    resetRegisteredIds,
    ensureFirstRun,
    close,
    options: getDepOptimizationConfig(config, ssr),
  }

  depsOptimizerMap.set(config, depsOptimizer)

  let newDepsDiscovered = false

  let newDepsToLog: string[] = []
  let newDepsToLogHandle: NodeJS.Timeout | undefined
  const logNewlyDiscoveredDeps = () => {
    if (newDepsToLog.length) {
      config.logger.info(
        colors.green(
          `✨ new dependencies optimized: ${depsLogString(newDepsToLog)}`,
        ),
        {
          timestamp: true,
        },
      )
      newDepsToLog = []
    }
  }

  let depOptimizationProcessing = newDepOptimizationProcessing()
  let depOptimizationProcessingQueue: DepOptimizationProcessing[] = []
  const resolveEnqueuedProcessingPromises = () => {
    // Resolve all the processings (including the ones which were delayed)
    for (const processing of depOptimizationProcessingQueue) {
      processing.resolve()
    }
    depOptimizationProcessingQueue = []
  }

  let enqueuedRerun: (() => void) | undefined
  let currentlyProcessing = false

  let firstRunCalled = !!cachedMetadata

  // During build, we wait for every module to be scanned before resolving
  // optimized deps loading for rollup on each rebuild. It will be recreated
  // after each buildStart.
  // During dev, if this is a cold run, we wait for static imports discovered
  // from the first request before resolving to minimize full page reloads.
  // On warm start or after the first optimization is run, we use a simpler
  // debounce strategy each time a new dep is discovered.
  let crawlEndFinder: CrawlEndFinder | undefined
  if (isBuild || !cachedMetadata) {
    crawlEndFinder = setupOnCrawlEnd(onCrawlEnd)
  }

  let optimizationResult:
    | {
        cancel: () => Promise<void>
        result: Promise<DepOptimizationResult>
      }
    | undefined

  let discover:
    | {
        cancel: () => Promise<void>
        result: Promise<Record<string, string>>
      }
    | undefined

  async function close() {
    closed = true
    crawlEndFinder?.cancel()
    await Promise.allSettled([
      discover?.cancel(),
      depsOptimizer.scanProcessing,
      optimizationResult?.cancel(),
    ])
  }

  if (!cachedMetadata) {
    // Enter processing state until crawl of static imports ends
    currentlyProcessing = true

    // Initialize discovered deps with manually added optimizeDeps.include info

    const deps: Record<string, string> = {}
    await addManuallyIncludedOptimizeDeps(deps, config, ssr)

    const discovered = toDiscoveredDependencies(
      config,
      deps,
      ssr,
      sessionTimestamp,
    )

    for (const depInfo of Object.values(discovered)) {
      addOptimizedDepInfo(metadata, 'discovered', {
        ...depInfo,
        processing: depOptimizationProcessing.promise,
      })
      newDepsDiscovered = true
    }

    if (config.optimizeDeps.noDiscovery) {
      // We don't need to scan for dependencies or wait for the static crawl to end
      // Run the first optimization run immediately
      runOptimizer()
    } else if (!isBuild) {
      // Important, the scanner is dev only
      depsOptimizer.scanProcessing = new Promise((resolve) => {
        // Runs in the background in case blocking high priority tasks
        ;(async () => {
          try {
            debug?.(colors.green(`scanning for dependencies...`))

            discover = discoverProjectDependencies(config)
            const deps = await discover.result
            discover = undefined

            // Add these dependencies to the discovered list, as these are currently
            // used by the preAliasPlugin to support aliased and optimized deps.
            // This is also used by the CJS externalization heuristics in legacy mode
            for (const id of Object.keys(deps)) {
              if (!metadata.discovered[id]) {
                addMissingDep(id, deps[id])
              }
            }

            const knownDeps = prepareKnownDeps()

            // For dev, we run the scanner and the first optimization
            // run on the background, but we wait until crawling has ended
            // to decide if we send this result to the browser or we need to
            // do another optimize step
            optimizationResult = runOptimizeDeps(config, knownDeps)
          } catch (e) {
            logger.error(e.stack || e.message)
          } finally {
            resolve()
            depsOptimizer.scanProcessing = undefined
          }
        })()
      })
    }
  }

  function startNextDiscoveredBatch() {
    newDepsDiscovered = false

    // Add the current depOptimizationProcessing to the queue, these
    // promises are going to be resolved once a rerun is committed
    depOptimizationProcessingQueue.push(depOptimizationProcessing)

    // Create a new promise for the next rerun, discovered missing
    // dependencies will be assigned this promise from this point
    depOptimizationProcessing = newDepOptimizationProcessing()
  }

  function prepareKnownDeps() {
    const knownDeps: Record<string, OptimizedDepInfo> = {}
    // Clone optimized info objects, fileHash, browserHash may be changed for them
    for (const dep of Object.keys(metadata.optimized)) {
      knownDeps[dep] = { ...metadata.optimized[dep] }
    }
    for (const dep of Object.keys(metadata.discovered)) {
      // Clone the discovered info discarding its processing promise
      const { processing, ...info } = metadata.discovered[dep]
      knownDeps[dep] = info
    }
    return knownDeps
  }

  async function runOptimizer(preRunResult?: DepOptimizationResult) {
    // a successful completion of the optimizeDeps rerun will end up
    // creating new bundled version of all current and discovered deps
    // in the cache dir and a new metadata info object assigned
    // to _metadata. A fullReload is only issued if the previous bundled
    // dependencies have changed.

    // if the rerun fails, _metadata remains untouched, current discovered
    // deps are cleaned, and a fullReload is issued

    // All deps, previous known and newly discovered are rebundled,
    // respect insertion order to keep the metadata file stable

    const isRerun = firstRunCalled
    firstRunCalled = true

    // Ensure that rerun is called sequentially
    enqueuedRerun = undefined

    // Ensure that a rerun will not be issued for current discovered deps
    if (debounceProcessingHandle) clearTimeout(debounceProcessingHandle)

    if (closed || Object.keys(metadata.discovered).length === 0) {
      currentlyProcessing = false
      return
    }

    currentlyProcessing = true

    try {
      let processingResult: DepOptimizationResult
      if (preRunResult) {
        processingResult = preRunResult
      } else {
        const knownDeps = prepareKnownDeps()
        startNextDiscoveredBatch()

        optimizationResult = runOptimizeDeps(config, knownDeps)
        processingResult = await optimizationResult.result
        optimizationResult = undefined
      }

      if (closed) {
        currentlyProcessing = false
        processingResult.cancel()
        resolveEnqueuedProcessingPromises()
        return
      }

      const newData = processingResult.metadata

      const needsInteropMismatch = findInteropMismatches(
        metadata.discovered,
        newData.optimized,
      )

      // After a re-optimization, if the internal bundled chunks change a full page reload
      // is required. If the files are stable, we can avoid the reload that is expensive
      // for large applications. Comparing their fileHash we can find out if it is safe to
      // keep the current browser state.
      const needsReload =
        needsInteropMismatch.length > 0 ||
        metadata.hash !== newData.hash ||
        Object.keys(metadata.optimized).some((dep) => {
          return (
            metadata.optimized[dep].fileHash !== newData.optimized[dep].fileHash
          )
        })

      const commitProcessing = async () => {
        await processingResult.commit()

        // While optimizeDeps is running, new missing deps may be discovered,
        // in which case they will keep being added to metadata.discovered
        for (const id in metadata.discovered) {
          if (!newData.optimized[id]) {
            addOptimizedDepInfo(newData, 'discovered', metadata.discovered[id])
          }
        }

        // If we don't reload the page, we need to keep browserHash stable
        if (!needsReload) {
          newData.browserHash = metadata.browserHash
          for (const dep in newData.chunks) {
            newData.chunks[dep].browserHash = metadata.browserHash
          }
          for (const dep in newData.optimized) {
            newData.optimized[dep].browserHash = (
              metadata.optimized[dep] || metadata.discovered[dep]
            ).browserHash
          }
        }

        // Commit hash and needsInterop changes to the discovered deps info
        // object. Allow for code to await for the discovered processing promise
        // and use the information in the same object
        for (const o in newData.optimized) {
          const discovered = metadata.discovered[o]
          if (discovered) {
            const optimized = newData.optimized[o]
            discovered.browserHash = optimized.browserHash
            discovered.fileHash = optimized.fileHash
            discovered.needsInterop = optimized.needsInterop
            discovered.processing = undefined
          }
        }

        if (isRerun) {
          newDepsToLog.push(
            ...Object.keys(newData.optimized).filter(
              (dep) => !metadata.optimized[dep],
            ),
          )
        }

        metadata = depsOptimizer.metadata = newData
        resolveEnqueuedProcessingPromises()
      }

      if (!needsReload) {
        await commitProcessing()

        if (!debug) {
          if (newDepsToLogHandle) clearTimeout(newDepsToLogHandle)
          newDepsToLogHandle = setTimeout(() => {
            newDepsToLogHandle = undefined
            logNewlyDiscoveredDeps()
          }, 2 * debounceMs)
        } else {
          debug(
            colors.green(
              `✨ ${
                !isRerun
                  ? `dependencies optimized`
                  : `optimized dependencies unchanged`
              }`,
            ),
          )
        }
      } else {
        if (newDepsDiscovered) {
          // There are newly discovered deps, and another rerun is about to be
          // executed. Avoid the current full reload discarding this rerun result
          // We don't resolve the processing promise, as they will be resolved
          // once a rerun is committed
          processingResult.cancel()

          debug?.(
            colors.green(
              `✨ delaying reload as new dependencies have been found...`,
            ),
          )
        } else {
          await commitProcessing()

          if (!debug) {
            if (newDepsToLogHandle) clearTimeout(newDepsToLogHandle)
            newDepsToLogHandle = undefined
            logNewlyDiscoveredDeps()
          }

          logger.info(
            colors.green(`✨ optimized dependencies changed. reloading`),
            {
              timestamp: true,
            },
          )
          if (needsInteropMismatch.length > 0) {
            config.logger.warn(
              `Mixed ESM and CJS detected in ${colors.yellow(
                needsInteropMismatch.join(', '),
              )}, add ${
                needsInteropMismatch.length === 1 ? 'it' : 'them'
              } to optimizeDeps.needsInterop to speed up cold start`,
              {
                timestamp: true,
              },
            )
          }

          fullReload()
        }
      }
    } catch (e) {
      logger.error(
        colors.red(`error while updating dependencies:\n${e.stack}`),
        { timestamp: true, error: e },
      )
      resolveEnqueuedProcessingPromises()

      // Reset missing deps, let the server rediscover the dependencies
      metadata.discovered = {}
    }

    currentlyProcessing = false
    // @ts-expect-error `enqueuedRerun` could exist because `debouncedProcessing` may run while awaited
    enqueuedRerun?.()
  }

  function fullReload() {
    if (server) {
      // Cached transform results have stale imports (resolved to
      // old locations) so they need to be invalidated before the page is
      // reloaded.
      server.moduleGraph.invalidateAll()

      server.ws.send({
        type: 'full-reload',
        path: '*',
      })
    }
  }

  async function rerun() {
    // debounce time to wait for new missing deps finished, issue a new
    // optimization of deps (both old and newly found) once the previous
    // optimizeDeps processing is finished
    const deps = Object.keys(metadata.discovered)
    const depsString = depsLogString(deps)
    debug?.(colors.green(`new dependencies found: ${depsString}`))
    runOptimizer()
  }

  function getDiscoveredBrowserHash(
    hash: string,
    deps: Record<string, string>,
    missing: Record<string, string>,
  ) {
    return getHash(
      hash + JSON.stringify(deps) + JSON.stringify(missing) + sessionTimestamp,
    )
  }

  function registerMissingImport(
    id: string,
    resolved: string,
  ): OptimizedDepInfo {
    const optimized = metadata.optimized[id]
    if (optimized) {
      return optimized
    }
    const chunk = metadata.chunks[id]
    if (chunk) {
      return chunk
    }
    let missing = metadata.discovered[id]
    if (missing) {
      // We are already discover this dependency
      // It will be processed in the next rerun call
      return missing
    }

    missing = addMissingDep(id, resolved)

    // Until the first optimize run is called, avoid triggering processing
    // We'll wait until the user codebase is eagerly processed by Vite so
    // we can get a list of every missing dependency before giving to the
    // browser a dependency that may be outdated, thus avoiding full page reloads

    if (!crawlEndFinder) {
      if (isBuild) {
        logger.error(
          'Vite Internal Error: Missing dependency found after crawling ended',
        )
      }
      // Debounced rerun, let other missing dependencies be discovered before
      // the running next optimizeDeps
      debouncedProcessing()
    }

    // Return the path for the optimized bundle, this path is known before
    // esbuild is run to generate the pre-bundle
    return missing
  }

  function addMissingDep(id: string, resolved: string) {
    newDepsDiscovered = true

    return addOptimizedDepInfo(metadata, 'discovered', {
      id,
      file: getOptimizedDepPath(id, config, ssr),
      src: resolved,
      // Adding a browserHash to this missing dependency that is unique to
      // the current state of known + missing deps. If its optimizeDeps run
      // doesn't alter the bundled files of previous known dependencies,
      // we don't need a full reload and this browserHash will be kept
      browserHash: getDiscoveredBrowserHash(
        metadata.hash,
        depsFromOptimizedDepInfo(metadata.optimized),
        depsFromOptimizedDepInfo(metadata.discovered),
      ),
      // loading of this pre-bundled dep needs to await for its processing
      // promise to be resolved
      processing: depOptimizationProcessing.promise,
      exportsData: extractExportsData(resolved, config, ssr),
    })
  }

  function debouncedProcessing(timeout = debounceMs) {
    if (!newDepsDiscovered) {
      return
    }
    // Debounced rerun, let other missing dependencies be discovered before
    // the running next optimizeDeps
    enqueuedRerun = undefined
    if (debounceProcessingHandle) clearTimeout(debounceProcessingHandle)
    if (newDepsToLogHandle) clearTimeout(newDepsToLogHandle)
    newDepsToLogHandle = undefined
    debounceProcessingHandle = setTimeout(() => {
      debounceProcessingHandle = undefined
      enqueuedRerun = rerun
      if (!currentlyProcessing) {
        enqueuedRerun()
      }
    }, timeout)
  }

  // During dev, onCrawlEnd is called once when the server starts and all static
  // imports after the first request have been crawled (dynamic imports may also
  // be crawled if the browser requests them right away).
  // During build, onCrawlEnd will be called once after each buildStart (so in
  // watch mode it will be called after each rebuild has processed every module).
  // All modules are transformed first in this case (both static and dynamic).
  async function onCrawlEnd() {
    // On build time, a missing dep appearing after onCrawlEnd is an internal error
    // On dev, switch after this point to a simple debounce strategy
    crawlEndFinder = undefined

    debug?.(colors.green(`✨ static imports crawl ended`))
    if (closed) {
      return
    }

    const crawlDeps = Object.keys(metadata.discovered)

    // Await for the scan+optimize step running in the background
    // It normally should be over by the time crawling of user code ended
    await depsOptimizer.scanProcessing

    if (!isBuild && optimizationResult) {
      const result = await optimizationResult.result
      optimizationResult = undefined
      currentlyProcessing = false

      const scanDeps = Object.keys(result.metadata.optimized)

      if (scanDeps.length === 0 && crawlDeps.length === 0) {
        debug?.(
          colors.green(
            `✨ no dependencies found by the scanner or crawling static imports`,
          ),
        )
        result.cancel()
        firstRunCalled = true
        return
      }

      const needsInteropMismatch = findInteropMismatches(
        metadata.discovered,
        result.metadata.optimized,
      )
      const scannerMissedDeps = crawlDeps.some((dep) => !scanDeps.includes(dep))
      const outdatedResult =
        needsInteropMismatch.length > 0 || scannerMissedDeps

      if (outdatedResult) {
        // Drop this scan result, and perform a new optimization to avoid a full reload
        result.cancel()

        // Add deps found by the scanner to the discovered deps while crawling
        for (const dep of scanDeps) {
          if (!crawlDeps.includes(dep)) {
            addMissingDep(dep, result.metadata.optimized[dep].src!)
          }
        }
        if (scannerMissedDeps) {
          debug?.(
            colors.yellow(
              `✨ new dependencies were found while crawling that weren't detected by the scanner`,
            ),
          )
        }
        debug?.(colors.green(`✨ re-running optimizer`))
        debouncedProcessing(0)
      } else {
        debug?.(
          colors.green(
            `✨ using post-scan optimizer result, the scanner found every used dependency`,
          ),
        )
        startNextDiscoveredBatch()
        runOptimizer(result)
      }
    } else {
      currentlyProcessing = false

      if (crawlDeps.length === 0) {
        debug?.(
          colors.green(
            `✨ no dependencies found while crawling the static imports`,
          ),
        )
        firstRunCalled = true
      } else {
        // queue the first optimizer run
        debouncedProcessing(0)
      }
    }
  }

  // Called during buildStart at build time, when build --watch is used.
  function resetRegisteredIds() {
    crawlEndFinder?.cancel()
    crawlEndFinder = setupOnCrawlEnd(onCrawlEnd)
  }

  function registerWorkersSource(id: string) {
    crawlEndFinder?.registerWorkersSource(id)
  }
  function delayDepsOptimizerUntil(id: string, done: () => Promise<any>) {
    if (crawlEndFinder && !depsOptimizer.isOptimizedDepFile(id)) {
      crawlEndFinder.delayDepsOptimizerUntil(id, done)
    }
  }
  function ensureFirstRun() {
    crawlEndFinder?.ensureFirstRun()
  }
}

const callCrawlEndIfIdleAfterMs = 50

interface CrawlEndFinder {
  ensureFirstRun: () => void
  registerWorkersSource: (id: string) => void
  delayDepsOptimizerUntil: (id: string, done: () => Promise<any>) => void
  cancel: () => void
}

function setupOnCrawlEnd(onCrawlEnd: () => void): CrawlEndFinder {
  const registeredIds = new Set<string>()
  const seenIds = new Set<string>()
  const workersSources = new Set<string>()
  let timeoutHandle: NodeJS.Timeout | undefined

  let cancelled = false
  function cancel() {
    cancelled = true
  }

  let crawlEndCalled = false
  function callOnCrawlEnd() {
    if (!cancelled && !crawlEndCalled) {
      crawlEndCalled = true
      onCrawlEnd()
    }
  }

  // If all the inputs are dependencies, we aren't going to get any
  // delayDepsOptimizerUntil(id) calls. We need to guard against this
  // by forcing a rerun if no deps have been registered
  let firstRunEnsured = false
  function ensureFirstRun() {
    if (!firstRunEnsured && seenIds.size === 0) {
      setTimeout(() => {
        if (seenIds.size === 0) {
          callOnCrawlEnd()
        }
      }, 200)
    }
    firstRunEnsured = true
  }

  function registerWorkersSource(id: string): void {
    workersSources.add(id)

    // Avoid waiting for this id, as it may be blocked by the rollup
    // bundling process of the worker that also depends on the optimizer
    registeredIds.delete(id)

    checkIfCrawlEndAfterTimeout()
  }

  function delayDepsOptimizerUntil(id: string, done: () => Promise<any>): void {
    if (!seenIds.has(id)) {
      seenIds.add(id)
      if (!workersSources.has(id)) {
        registeredIds.add(id)
        done()
          .catch(() => {})
          .finally(() => markIdAsDone(id))
      }
    }
  }
  function markIdAsDone(id: string): void {
    registeredIds.delete(id)
    checkIfCrawlEndAfterTimeout()
  }

  function checkIfCrawlEndAfterTimeout() {
    if (cancelled || registeredIds.size > 0) return

    if (timeoutHandle) clearTimeout(timeoutHandle)
    timeoutHandle = setTimeout(
      callOnCrawlEndWhenIdle,
      callCrawlEndIfIdleAfterMs,
    )
  }
  async function callOnCrawlEndWhenIdle() {
    if (cancelled || registeredIds.size > 0) return
    callOnCrawlEnd()
  }

  return {
    ensureFirstRun,
    registerWorkersSource,
    delayDepsOptimizerUntil,
    cancel,
  }
}

async function createDevSsrDepsOptimizer(
  config: ResolvedConfig,
): Promise<void> {
  const metadata = await optimizeServerSsrDeps(config)

  const depsOptimizer = {
    metadata,
    isOptimizedDepFile: createIsOptimizedDepFile(config),
    isOptimizedDepUrl: createIsOptimizedDepUrl(config),
    getOptimizedDepId: (depInfo: OptimizedDepInfo) =>
      `${depInfo.file}?v=${depInfo.browserHash}`,

    registerMissingImport: () => {
      throw new Error(
        'Vite Internal Error: registerMissingImport is not supported in dev SSR',
      )
    },
    // noop, there is no scanning during dev SSR
    // the optimizer blocks the server start
    run: () => {},
    registerWorkersSource: (id: string) => {},
    delayDepsOptimizerUntil: (id: string, done: () => Promise<any>) => {},
    resetRegisteredIds: () => {},
    ensureFirstRun: () => {},

    close: async () => {},
    options: config.ssr.optimizeDeps,
  }
  devSsrDepsOptimizerMap.set(config, depsOptimizer)
}

function findInteropMismatches(
  discovered: Record<string, OptimizedDepInfo>,
  optimized: Record<string, OptimizedDepInfo>,
) {
  const needsInteropMismatch = []
  for (const dep in discovered) {
    const discoveredDepInfo = discovered[dep]
    const depInfo = optimized[dep]
    if (depInfo) {
      if (
        discoveredDepInfo.needsInterop !== undefined &&
        depInfo.needsInterop !== discoveredDepInfo.needsInterop
      ) {
        // This only happens when a discovered dependency has mixed ESM and CJS syntax
        // and it hasn't been manually added to optimizeDeps.needsInterop
        needsInteropMismatch.push(dep)
        debug?.(colors.cyan(`✨ needsInterop mismatch detected for ${dep}`))
      }
    }
  }
  return needsInteropMismatch
}
