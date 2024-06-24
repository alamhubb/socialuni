import PlatformModuleLoadUtil from "./PlatformModuleLoadUtil";

let nativeUtil

const modules = import.meta.glob('../../../qing-compat-js-*/src/util/StorageUtil.ts',{eager:true})

nativeUtil = PlatformModuleLoadUtil.getModuleDefault(modules)

export default class QingStorageUtil {
    static get nativeUtil(): any {
        return nativeUtil
    }
}
