import AlertUtil from "socialuni-native-uni/src/util/AlertUtil";
import SocialuniAlertUtil from "./SocialuniAlertUtil";
import SocialuniAppNativeUtil from "./SocialuniAppNativeUtil";
import SocialuniToastUtil from "./SocialuniToastUtil";
import ToastUtil from "socialuni-native-uni/src/util/ToastUtil";
import StorageUtil from "socialuni-native-uni/src/util/StorageUtil";
import SocialuniStorageUtil from "./SocialuniStorageUtil";
import SocialuniRouterUtil from "./SocialuniRouterUtil";
import RouterUtil from "socialuni-native-uni/src/util/RouterUtil";
import NativeUtil from 'socialuni-native-uni/src/util/NativeUtil'

export default class SocialuniAppUtil {
    static get UniUtil(): NativeUtil {
        return SocialuniAppNativeUtil.nativeUtil
    }

    static get AlertUtil(): AlertUtil {
        return SocialuniAlertUtil.nativeUtil
    }

    static get ToastUtil(): ToastUtil {
        return SocialuniToastUtil.nativeUtil
    }

    static get StorageUtil(): StorageUtil {
        return SocialuniStorageUtil.nativeUtil
    }

    static get RouterUtil(): RouterUtil {
        return SocialuniRouterUtil.nativeUtil
    }

}
