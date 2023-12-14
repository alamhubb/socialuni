import PlatformModuleLoadUtil from "./PlatformModuleLoadUtil";

let nativeUtil

const modules = import.meta.glob('../../../qingjs-*/src/util/StorageUtil.ts',{eager:true})

nativeUtil = PlatformModuleLoadUtil.getModuleDefault(modules)

export default class QingStorageUtil {
    static get nativeUtil(): any {
        return nativeUtil
    }
}
