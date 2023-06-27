import PlatformAutoLoadUtil from "./PlatformAutoLoadUtil";

let nativeUtil

const modules = import.meta.globEager('../../../*/src/util/NativeUtil.ts')

nativeUtil = PlatformAutoLoadUtil.getNativeUtil(modules)

export default class SocialuniAppNativeUtil {
    static get nativeUtil(): any {
        return nativeUtil
    }
}
