import PlatformModuleLoadUtil from "./PlatformModuleLoadUtil";

let nativeUtil

const modules = import.meta.globEager('../../../socialuni-native-*/src/util/StorageUtil.ts')

nativeUtil = PlatformModuleLoadUtil.getModule(modules)

export default class SocialuniStorageUtil {
    static get nativeUtil(): any {
        return nativeUtil
    }
}
