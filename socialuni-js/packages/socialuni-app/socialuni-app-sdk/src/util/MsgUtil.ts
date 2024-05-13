import AppMsg from "socialuni-constant/constant/AppMsg";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import QingAppUtil from 'qingjs/src/util/QingAppUtil'
import { socialuniConfigModule } from '../store/SocialuniConfigModule'


export default class MsgUtil {
    static showUploadLoading() {
        QingAppUtil.NativeUtil.showLoading('上传中')
    }

    static systemErrorMsg() {
        QingAppUtil.AlertUtil.hint(socialuniConfigModule.appMoreConfig.errorMsg604SystemError)
    }


    /*static identityAuthHint() {
        this.unLoginMessage()
        /!*let msg
        if (socialuniUserModule.user.identityAuth) {
            msg = '您已完成成年认证'
        } else {
            msg = '未获得成年认证标识'
        }
        QingAppUtil.AlertUtil.confirm(msg + '，是否继续前往成年认证页面', '前往').then(() => {
            PageUtil.toIdentityAuthPage()
        })*!/
    }*/

    static iosDisablePay() {
        return QingAppUtil.AlertUtil.hint(AppMsg.iosDisablePayMsg)
    }

    static notMpDisablePay() {
        return QingAppUtil.AlertUtil.hint(AppMsg.notMpDisablePayMsg)
    }

    static notPay() {
        return QingAppUtil.AlertUtil.hint(AppMsg.notPayMsg)
    }

    static payFailMsg() {
        return QingAppUtil.AlertUtil.hint(AppMsg.payFailMsg)
    }

    static cantPopupPromptToast() {
        if (socialuniSystemModule.isIosOrMpQQ) {
            QingAppUtil.ToastUtil.toastLong('如遇到无法弹出输入框，请重启应用')
        }
    }
}
