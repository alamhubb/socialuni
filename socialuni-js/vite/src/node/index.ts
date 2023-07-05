import type * as Rollup from 'rollup'

export type { Rollup }
export * from './config'
export { createServer } from './server'
export { preview } from './preview'
export { build } from './build'
export { optimizeDeps } from './optimizer'
export { formatPostcssSourceMap, preprocessCSS } from './plugins/css'
export { transformWithEsbuild } from './plugins/esbuild'
export { resolvePackageEntry } from './plugins/resolve'
export { resolvePackageData } from './packages'
export { buildErrorMessage } from './server/middlewares/error'
export * from './publicUtils'

// additional types
export type { FilterPattern } from './utils'
export type { CorsOptions, CorsOrigin, CommonServerOptions } from './http'
export type {
  ViteDevServer,
  ServerOptions,
  FileSystemServeOptions,
  ServerHook,
  ResolvedServerOptions,
  ResolvedServerUrls,
} from './server'
export type {
  BuildOptions,
  LibraryOptions,
  LibraryFormats,
  RenderBuiltAssetUrl,
  ResolvedBuildOptions,
} from './build'
export type {
  PreviewOptions,
  PreviewServer,
  PreviewServerForHook,
  PreviewServerHook,
  ResolvedPreviewOptions,
} from './preview'
export type {
  DepOptimizationMetadata,
  DepOptimizationOptions,
  DepOptimizationConfig,
  DepOptimizationResult,
  DepOptimizationProcessing,
  OptimizedDepInfo,
  DepsOptimizer,
  ExportsData,
} from './optimizer'
export type {
  ResolvedSSROptions,
  SsrDepOptimizationOptions,
  SSROptions,
  SSRFormat,
  SSRTarget,
} from './ssr'
export type { Plugin, HookHandler } from './plugin'
export type { PackageCache, PackageData } from './packages'
export type {
  Logger,
  LogOptions,
  LogErrorOptions,
  LogLevel,
  LogType,
  LoggerOptions,
} from './logger'
export type {
  IndexHtmlTransform,
  IndexHtmlTransformHook,
  IndexHtmlTransformContext,
  IndexHtmlTransformResult,
  HtmlTagDescriptor,
} from './plugins/html'
export type {
  CSSOptions,
  CSSModulesOptions,
  PreprocessCSSResult,
} from './plugins/css'
export type { JsonOptions } from './plugins/json'
export type { TransformOptions as EsbuildTransformOptions } from 'esbuild'
export type { ESBuildOptions, ESBuildTransformResult } from './plugins/esbuild'
export type { Manifest, ManifestChunk } from './plugins/manifest'
export type { ResolveOptions, InternalResolveOptions } from './plugins/resolve'
export type { SplitVendorChunkCache } from './plugins/splitVendorChunk'

export type {
  WebSocketServer,
  WebSocketClient,
  WebSocketCustomListener,
} from './server/ws'
export type { PluginContainer } from './server/pluginContainer'
export type { ModuleGraph, ModuleNode, ResolvedUrl } from './server/moduleGraph'
export type { SendOptions } from './server/send'
export type { ProxyOptions } from './server/middlewares/proxy'
export type {
  TransformOptions,
  TransformResult,
} from './server/transformRequest'
export type { HmrOptions, HmrContext } from './server/hmr'

export type {
  HMRPayload,
  ConnectedPayload,
  UpdatePayload,
  Update,
  FullReloadPayload,
  CustomPayload,
  PrunePayload,
  ErrorPayload,
} from 'types/hmrPayload'
export type {
  CustomEventMap,
  InferCustomEventPayload,
  InvalidatePayload,
} from 'types/customEvent'
export type {
  ImportGlobFunction,
  ImportGlobEagerFunction,
  ImportGlobOptions,
  GeneralImportGlobOptions,
  KnownAsTypeMap,
} from 'types/importGlob'
export type { ChunkMetadata } from 'types/metadata'

// dep types
export type {
  AliasOptions,
  MapToFunction,
  ResolverFunction,
  ResolverObject,
  Alias,
} from 'dep-types/alias'
export type { Connect } from 'dep-types/connect'
export type { WebSocket, WebSocketAlias } from 'dep-types/ws'
export type { HttpProxy } from 'dep-types/http-proxy'
export type {
  FSWatcher,
  WatchOptions,
  AwaitWriteFinishOptions,
} from 'dep-types/chokidar'
export type { Terser } from 'dep-types/terser'
export type { RollupCommonJSOptions } from 'dep-types/commonjs'
export type { RollupDynamicImportVarsOptions } from 'dep-types/dynamicImportVars'
export type { Matcher, AnymatchPattern, AnymatchFn } from 'dep-types/anymatch'
