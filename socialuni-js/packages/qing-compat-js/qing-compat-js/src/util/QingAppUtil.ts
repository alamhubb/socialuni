import QingAlertUtil from "./QingAlertUtil";
import QingAppNativeUtil from "./QingAppNativeUtil";
import QingToastUtil from "./QingToastUtil";
import QingStorageUtil from "./QingStorageUtil";
import QingRouterUtil from "./QingRouterUtil";
import {SocialuniRouterUtilInterface} from "../interface/SocialuniRouterUtilInterface";
import {SocialuniAlertUtilInterface} from "../interface/SocialuniAlertUtilInterface";
import {SocialuniToastUtilInterface} from "../interface/SocialuniToastUtilInterface";
import {SocialuniNativeUtilInterface} from "../interface/SocialuniNativeUtilInterface";
import {SocialuniLodingInterface} from "../interface/SocialuniLodingInterface";
import QingLoadingUtil from "./QingLoadingUtil";

export default class QingAppUtil {
    static get NativeUtil(): SocialuniNativeUtilInterface {
        return QingAppNativeUtil.nativeUtil
    }

    static get loadingUtil(): SocialuniLodingInterface {
        return QingLoadingUtil.nativeUtil
    }

    static get AlertUtil(): SocialuniAlertUtilInterface {
        return QingAlertUtil.nativeUtil
    }

    static get ToastUtil(): SocialuniToastUtilInterface {
        return QingToastUtil.nativeUtil
    }

    static get StorageUtil(): any {
        return QingStorageUtil.nativeUtil
    }

    static get RouterUtil(): SocialuniRouterUtilInterface {
        return QingRouterUtil.nativeUtil
    }
}
