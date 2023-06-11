import UniUtil from "./UniUtil";
import AlertUtil from "socialuni-native-uni/src/util/AlertUtil";
import SocialuniAlertUtil from "./SocialuniAlertUtil";
import SocialuniAppNativeUtil from "./SocialuniAppNativeUtil";
import SocialuniToastUtil from "./SocialuniToastUtil";
import ToastUtil from "socialuni-native-uni/src/util/ToastUtil";

export default class SocialuniAppUtil {
    static get nativeUtil(): UniUtil {
        return SocialuniAppNativeUtil.nativeUtil
    }

    static get AlertUtil(): AlertUtil {
        return SocialuniAlertUtil.nativeUtil
    }
    static get ToastUtil(): ToastUtil {
        return SocialuniToastUtil.nativeUtil
    }
}
