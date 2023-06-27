import {SocialuniAlertUtilInterface} from "../interface/SocialuniAlertUtilInterface";
import PlatformModuleLoadUtil from "./PlatformModuleLoadUtil";

let nativeUtil: SocialuniAlertUtilInterface

const modules = import.meta.globEager('../../../*/src/util/AlertUtil.ts')

nativeUtil = PlatformModuleLoadUtil.getModule(modules)

export default class SocialuniAlertUtil {
    static get nativeUtil(): SocialuniAlertUtilInterface {
        return nativeUtil
    }
}
