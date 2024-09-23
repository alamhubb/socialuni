import {findNearestMainPackageData, resolvePackageData} from "../packages";

export const bareImportRE = /^(?![a-zA-Z]:)[\w@](?!.*:\/\/)/;
export const deepImportRE = /^([^@][^/]*)\/|^(@[^/]+\/[^/]+)\//;
import path from 'node:path'
import fs from 'node:fs'
import {cleanUrl} from "../../shared/utils";

export function tryNodeResolve(
    id: string,
    importer: string | null | undefined,
    options: any,
    targetWeb: boolean,
    depsOptimizer?: any,
    ssr: boolean = false,
    externalize?: boolean,
    allowLinkedExternal: boolean = true,
): PartialResolvedId | undefined {
  const {root, dedupe, isBuild, preserveSymlinks, packageCache} = options

  // check for deep import, e.g. "my-lib/foo"
  const deepMatch = deepImportRE.exec(id)

  console.log(1111)
  // package name doesn't include postfixes
  // trim them to support importing package with queries (e.g. `import css from 'normalize.css?inline'`)
  const pkgId = deepMatch ? deepMatch[1] || deepMatch[2] : cleanUrl(id)

  let basedir: string
  if (dedupe?.includes(pkgId)) {
    console.log(22222)
    basedir = root
  } else if (
      importer &&
      path.isAbsolute(importer) &&
      // css processing appends `*` for importer
      (importer[importer.length - 1] === '*' || fs.existsSync(cleanUrl(importer)))
  ) {
    console.log(33333)
    basedir = path.dirname(importer)
  } else {
    console.log(44444)
    basedir = root
  }
  console.log(5557777)
  const pkg = resolvePackageData(pkgId, basedir, preserveSymlinks, packageCache)
  console.log(pkg)
  if (!pkg) {
    console.log(66666)
    // if import can't be found, check if it's an optional peer dep.
    // if so, we can resolve to a special id that errors only when imported.
    if (
        basedir !== root && // root has no peer dep
        !isBuiltin(id) &&
        !id.includes('\0') &&
        bareImportRE.test(id)
    ) {
      console.log(77777)
      const mainPkg = findNearestMainPackageData(basedir, packageCache)?.data
      if (mainPkg) {
        console.log(88888)
        const pkgName = getNpmPackageName(id)
        if (
            pkgName != null &&
            mainPkg.peerDependencies?.[pkgName] &&
            mainPkg.peerDependenciesMeta?.[pkgName]?.optional
        ) {
          console.log(999999)
          console.log(mainPkg.name)
          return {
            id: `${optionalPeerDepId}:${id}:${mainPkg.name}`,
          }
        }
      }
    }
    return
  }

  console.log('deepmatch:', !!deepMatch)
  const resolveId = deepMatch ? resolveDeepImport : resolvePackageEntry
  const unresolvedId = deepMatch ? '.' + id.slice(pkgId.length) : id

  console.log(11111)
  let resolved: string | undefined
  try {
    resolved = resolveId(unresolvedId, pkg, targetWeb, options)
    console.log(2222)
    console.log(resolved)
  } catch (err) {
    if (!options.tryEsmOnly) {
      throw err
    }
  }
  console.log(3333)

  return {
    id: resolved,
    moduleSideEffects: pkg.hasSideEffects(resolved),
  }
}
