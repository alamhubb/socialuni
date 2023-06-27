import PlatformModuleLoadUtil from "./PlatformModuleLoadUtil";

let nativeUtil

const modules = import.meta.globEager('../../../*/src/util/NativeUtil.ts')

nativeUtil = PlatformModuleLoadUtil.getModule(modules)

export default class SocialuniAppNativeUtil {
    static get nativeUtil(): any {
        return nativeUtil
    }
}
