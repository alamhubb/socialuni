import PlatformAutoLoadUtil from "./PlatformAutoLoadUtil";

let nativeUtil

const modules = import.meta.globEager('../../../*/src/util/RouterUtil.ts')

nativeUtil = PlatformAutoLoadUtil.getNativeUtil(modules)

export default class SocialuniRouterUtil {
    static get nativeUtil(): any {
        return nativeUtil
    }
}
