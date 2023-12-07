import PlatformModuleLoadUtil from "./PlatformModuleLoadUtil";

let nativeUtil

const modules = import.meta.glob('../../../socialuni-native-*/src/util/StorageUtil.ts',{eager:true})

nativeUtil = PlatformModuleLoadUtil.getModuleDefault(modules)

export default class SocialuniStorageUtil {
    static get nativeUtil(): any {
        return nativeUtil
    }
}
