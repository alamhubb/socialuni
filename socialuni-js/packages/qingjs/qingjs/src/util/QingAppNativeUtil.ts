import PlatformModuleLoadUtil from "./PlatformModuleLoadUtil";
import {SocialuniNativeUtilInterface} from "../interface/SocialuniNativeUtilInterface";

let nativeUtil

const modules = import.meta.glob('../../../*/src/util/NativeUtil.ts',{eager:true})

nativeUtil = PlatformModuleLoadUtil.getModuleDefault(modules)

export default class QingAppNativeUtil {
    static get nativeUtil(): SocialuniNativeUtilInterface {
        return nativeUtil
    }
}
