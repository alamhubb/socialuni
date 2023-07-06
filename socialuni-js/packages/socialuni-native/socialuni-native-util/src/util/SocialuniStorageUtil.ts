import PlatformModuleLoadUtil from "./PlatformModuleLoadUtil";

let nativeUtil

const modules = import.meta.globEager('../../../socialuni-native-*/src/util/StorageUtil.ts')

nativeUtil = PlatformModuleLoadUtil.getModuleDefault(modules)

export default class SocialuniStorageUtil {
    static get nativeUtil(): any {
        return nativeUtil
    }
}
