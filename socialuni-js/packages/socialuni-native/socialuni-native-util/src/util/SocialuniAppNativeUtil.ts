import PlatformModuleLoadUtil from "./PlatformModuleLoadUtil";
import {SocialuniNativeUtilInterface} from "../interface/SocialuniNativeUtilInterface";

let nativeUtil

const modules = import.meta.globEager('../../../*/src/util/NativeUtil.ts')

nativeUtil = PlatformModuleLoadUtil.getModuleDefault(modules)

export default class SocialuniAppNativeUtil {
    static get nativeUtil(): SocialuniNativeUtilInterface {
        return nativeUtil
    }
}
