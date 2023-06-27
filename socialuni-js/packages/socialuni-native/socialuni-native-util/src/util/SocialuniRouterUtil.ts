import PlatformModuleLoadUtil from "./PlatformModuleLoadUtil";

let nativeUtil

const modules = import.meta.globEager('../../../*/src/util/RouterUtil.ts')

nativeUtil = PlatformModuleLoadUtil.getModule(modules)

export default class SocialuniRouterUtil {
    static get nativeUtil(): any {
        return nativeUtil
    }
}
