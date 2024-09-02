import PlatformModuleLoadUtil from "./PlatformModuleLoadUtil";
import {SocialuniStorageUtilInterface} from "../interface/SocialuniStorageUtilInterface";

let nativeUtil

const modules = import.meta.glob('../../../qing-compat-js-*/src/util/StorageUtil.ts',{eager:true})

nativeUtil = PlatformModuleLoadUtil.getModuleDefault(modules)

export default class QingStorageUtil {
    static get nativeUtil(): SocialuniStorageUtilInterface {
        return nativeUtil
    }
}
