import SocialuniAlertUtil from "./SocialuniAlertUtil";
import SocialuniAppNativeUtil from "./SocialuniAppNativeUtil";
import SocialuniToastUtil from "./SocialuniToastUtil";
import SocialuniStorageUtil from "./SocialuniStorageUtil";
import SocialuniRouterUtil from "./SocialuniRouterUtil";
import {SocialuniRouterUtilInterface} from "../interface/SocialuniRouterUtilInterface";
import {SocialuniAlertUtilInterface} from "../interface/SocialuniAlertUtilInterface";
import {SocialuniToastUtilInterface} from "../interface/SocialuniToastUtilInterface";
import {SocialuniNativeUtilInterface} from "../interface/SocialuniNativeUtilInterface";

export default class SocialuniAppUtil {
    static get NativeUtil(): SocialuniNativeUtilInterface {
        return SocialuniAppNativeUtil.nativeUtil
    }

    static get AlertUtil(): SocialuniAlertUtilInterface {
        return SocialuniAlertUtil.nativeUtil
    }

    static get ToastUtil(): SocialuniToastUtilInterface {
        return SocialuniToastUtil.nativeUtil
    }

    static get StorageUtil(): any {
        return SocialuniStorageUtil.nativeUtil
    }

    static get RouterUtil(): SocialuniRouterUtilInterface {
        return SocialuniRouterUtil.nativeUtil
    }
}
