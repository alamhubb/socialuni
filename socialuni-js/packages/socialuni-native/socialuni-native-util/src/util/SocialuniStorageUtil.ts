import PlatformAutoLoadUtil from "./PlatformAutoLoadUtil";

let nativeUtil

const modules = import.meta.globEager('../../../socialuni-native-*/src/util/StorageUtil.ts')

nativeUtil = PlatformAutoLoadUtil.getNativeUtil(modules)

export default class SocialuniStorageUtil {
    static get nativeUtil(): any {
        console.log(nativeUtil)
        return nativeUtil
    }
}
