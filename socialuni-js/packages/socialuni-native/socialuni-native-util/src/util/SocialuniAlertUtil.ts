import {SocialuniAlertUtilInterface} from "../interface/SocialuniAlertUtilInterface";
import PlatformAutoLoadUtil from "./PlatformAutoLoadUtil";

let nativeUtil: SocialuniAlertUtilInterface

const modules = import.meta.globEager('../../../*/src/util/AlertUtil.ts')

nativeUtil = PlatformAutoLoadUtil.getNativeUtil(modules)

export default class SocialuniAlertUtil {
    static get nativeUtil(): SocialuniAlertUtilInterface {
        return nativeUtil
    }
}
