import AlertUtil from 'socialuni-util/src/util/AlertUtil'
import ToastUtil from 'socialuni-util/src/util/ToastUtil'
import PageUtil from './PageUtil'
import UniUtil from "./UniUtil";
import AppMsg from "socialuni-constant/constant/AppMsg";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";;

export default class MsgUtil {
    static showUploadLoading() {
        UniUtil.showLoading('上传中')
    }

    static systemErrorMsg() {
        // AlertUtil.hint(socialuniConfigModule.appMoreConfig.errorMsg604SystemError)
    }

    static unUploadImg() {
        AlertUtil.confirm('请完善用户信息绑定手机号并上传照片，才能进行此项操作，是否前往完善信息', '前往').then(() => {
            PageUtil.toMinePage()
        })
    }

    static async uploadImgNeedAuthMsg() {
        await AlertUtil.confirm('未进行成年认证，发布包含人物的图像将不予展示，是否前往进行成年认证', '前往').then(() => {
            PageUtil.toIdentityAuthPage()
        })
    }

    /*static identityAuthHint() {
        this.unLoginMessage()
        /!*let msg
        if (socialuniUserModule.user.identityAuth) {
            msg = '您已完成成年认证'
        } else {
            msg = '未获得成年认证标识'
        }
        AlertUtil.confirm(msg + '，是否继续前往成年认证页面', '前往').then(() => {
            PageUtil.toIdentityAuthPage()
        })*!/
    }*/

    static iosDisablePay() {
        return AlertUtil.hint(AppMsg.iosDisablePayMsg)
    }

    static notMpDisablePay() {
        return AlertUtil.hint(AppMsg.notMpDisablePayMsg)
    }

    static notPay() {
        return AlertUtil.hint(AppMsg.notPayMsg)
    }

    static payFailMsg() {
        return AlertUtil.hint(AppMsg.payFailMsg)
    }

    static cantPopupPromptToast() {
        if (socialuniSystemModule.isIosAndMpQQ) {
            ToastUtil.toastLong('如遇到无法弹出输入框，请重启应用')
        }
    }
}
