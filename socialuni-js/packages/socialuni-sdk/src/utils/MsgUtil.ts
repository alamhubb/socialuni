import AlertUtil from 'socialuni-sdk/src/utils/AlertUtil'
import ToastUtil from 'socialuni-sdk/src/utils/ToastUtil'
import PageUtil from './PageUtil'
import {socialConfigModule, socialUserModule} from "socialuni-sdk/src/store/store";
// import {socialConfigModule} from "socialuni-sdk/src/store/store";
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";
import UniUtil from "./UniUtil";
import AppMsg from "socialuni-constant/constant/AppMsg";
import {socialSystemModule} from "socialuni-sdk/src/store/store";

export default class MsgUtil {
    static unBindPhoneNum() {
        const user: CenterUserDetailRO = socialUserModule.user
        if (!user) {
            MsgUtil.unLoginMessage()
        } else {
            // 如果登录了仅仅没绑定手机号，则提示跳转，区分qq和微信不同
            AlertUtil.confirm('绑定手机号才能发布内容，是否前往绑定手机号页面')
                .then(() => {
                    PageUtil.toPhonePage()
                })
        }
    }

    static unLoginMessage() {
        if (!socialUserModule.user) {
            AlertUtil.info(socialConfigModule.appMoreConfig.errorMsg601UnLogin)
                .then(() => {
                    // 没token才执行登录,有token证明已经登录，如果有错误应该清空token在执行这个
                    PageUtil.toMinePage()
                })
            throw new Error('未登录')
        }
    }

    static showUploadLoading() {
        UniUtil.showLoading('上传中')
    }

    static systemErrorMsg() {
        // AlertUtil.hint(socialConfigModule.appMoreConfig.errorMsg604SystemError)
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

    static identityAuthHint() {
        this.unLoginMessage()
        /*let msg
        if (socialUserModule.user.identityAuth) {
            msg = '您已完成成年认证'
        } else {
            msg = '未获得成年认证标识'
        }
        AlertUtil.confirm(msg + '，是否继续前往成年认证页面', '前往').then(() => {
            PageUtil.toIdentityAuthPage()
        })*/
    }

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
        if (socialSystemModule.isIosAndMpQQ) {
            ToastUtil.toastLong('如遇到无法弹出输入框，请重启应用')
        }
    }
}
