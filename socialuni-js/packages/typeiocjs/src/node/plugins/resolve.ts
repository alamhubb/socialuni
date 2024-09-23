import {resolvePackageData} from "../packages";

export const bareImportRE = /^(?![a-zA-Z]:)[\w@](?!.*:\/\/)/;
export const deepImportRE = /^([^@][^/]*)\/|^(@[^/]+\/[^/]+)\//;
import {cleanUrl} from "../../shared/utils";
import {optionalPeerDepId, resolveDeepImport, resolvePackageEntry} from "./resolve";
import {PartialResolvedId} from "rollup";

export function tryNodeResolveNew(
    id: string,
    importer: string | null | undefined,
    options: any,
    targetWeb: boolean,
    depsOptimizer?: any,
    ssr: boolean = false,
    externalize?: boolean,
    allowLinkedExternal: boolean = true,
): PartialResolvedId | undefined {
    const {root, dedupe, preserveSymlinks, packageCache} = options

    // check for deep import, e.g. "my-lib/foo"
    const deepMatch = deepImportRE.exec(id)

    // console.log(packageCache)
    // package name doesn't include postfixes
    // trim them to support importing package with queries (e.g. `import css from 'normalize.css?inline'`)
    //获取包id
    const pkgId = deepMatch ? deepMatch[1] || deepMatch[2] : cleanUrl(id)

    let basedir: string = root
    // console.log(pkgId)
    // console.log(basedir)
    const pkg = resolvePackageData(pkgId, basedir, preserveSymlinks, packageCache)
    if (pkg) {
        let resolved = `${pkg.dir}/${id.replace(`${pkgId}/`, '')}`;
        return {
            id: resolved,
        }
    } else {
        console.error('youshuzhi meifanhui')
    }
}
