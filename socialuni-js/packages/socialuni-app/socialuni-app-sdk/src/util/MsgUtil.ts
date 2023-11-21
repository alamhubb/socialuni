import AppMsg from "socialuni-constant/constant/AppMsg";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import SocialuniAppUtil from 'socialuni-native-util/src/util/SocialuniAppUtil'
import { socialuniConfigModule } from '../store/SocialuniConfigModule'


export default class MsgUtil {
    static showUploadLoading() {
        SocialuniAppUtil.NativeUtil.showLoading('上传中')
    }

    static systemErrorMsg() {
        SocialuniAppUtil.AlertUtil.hint(socialuniConfigModule.appMoreConfig.errorMsg604SystemError)
    }


    /*static identityAuthHint() {
        this.unLoginMessage()
        /!*let msg
        if (socialuniUserModule.user.identityAuth) {
            msg = '您已完成成年认证'
        } else {
            msg = '未获得成年认证标识'
        }
        SocialuniAppUtil.AlertUtil.confirm(msg + '，是否继续前往成年认证页面', '前往').then(() => {
            PageUtil.toIdentityAuthPage()
        })*!/
    }*/

    static iosDisablePay() {
        return SocialuniAppUtil.AlertUtil.hint(AppMsg.iosDisablePayMsg)
    }

    static notMpDisablePay() {
        return SocialuniAppUtil.AlertUtil.hint(AppMsg.notMpDisablePayMsg)
    }

    static notPay() {
        return SocialuniAppUtil.AlertUtil.hint(AppMsg.notPayMsg)
    }

    static payFailMsg() {
        return SocialuniAppUtil.AlertUtil.hint(AppMsg.payFailMsg)
    }

    static cantPopupPromptToast() {
        if (socialuniSystemModule.isIosOrMpQQ) {
            SocialuniAppUtil.ToastUtil.toastLong('如遇到无法弹出输入框，请重启应用')
        }
    }
}
