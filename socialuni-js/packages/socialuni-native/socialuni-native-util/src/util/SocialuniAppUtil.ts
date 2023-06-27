import SocialuniAlertUtil from "./SocialuniAlertUtil";
import SocialuniAppNativeUtil from "./SocialuniAppNativeUtil";
import SocialuniToastUtil from "./SocialuniToastUtil";
import SocialuniStorageUtil from "./SocialuniStorageUtil";
import SocialuniRouterUtil from "./SocialuniRouterUtil";
import {SocialuniRouterUtilInterface} from "../interface/SocialuniRouterUtilInterface";

export default class SocialuniAppUtil {
    static get UniUtil(): any {
        return SocialuniAppNativeUtil.nativeUtil
    }

    static get AlertUtil(): any {
        return SocialuniAlertUtil.nativeUtil
    }

    static get ToastUtil(): any {
        return SocialuniToastUtil.nativeUtil
    }

    static get StorageUtil(): any {
        return SocialuniStorageUtil.nativeUtil
    }

    static get RouterUtil(): SocialuniRouterUtilInterface {
        return SocialuniRouterUtil.nativeUtil
    }
}
