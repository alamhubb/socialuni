import QQUtils from './QQUtils'
import MsgUtil from "./MsgUtil"
import Constants from "socialuni-constant/constant/Constant"
import AppMsg from "socialuni-constant/constant/AppMsg"
import ToastUtil from "socialuni-util/src/util/ToastUtil"
import WxUtils from "./WxUtils"
import MPUtil from "socialuni-util/src/util/MPUtil"
import APPUtil from "./APPUtil"
import UserPayResultVO from "socialuni-base-api/src/model/user/UserPayResultVO";


// 统一处理各平台的订阅
export default class PlatformUtils {
    // talk相关订阅
   /* static requestSubscribeTalk() {
        // #ifdef MP-WEIXIN
        PlatformUtils.requestSubscribeMessage(socialPlatformModule.wx_talkTemplateIds)
        // #endif
        // #ifdef MP-QQ
        PlatformUtils.requestSubscribeMessage(socialPlatformModule.qq_talkTemplateIds)
        // #endif
    }

    // Comment相关订阅
    static requestSubscribeComment() {
        // #ifdef MP-WEIXIN
        PlatformUtils.requestSubscribeMessage(socialPlatformModule.wx_commentTemplateIds)
        // #endif
        // #ifdef MP-QQ
        PlatformUtils.requestSubscribeMessage(socialPlatformModule.qq_commentTemplateIds)
        // #endif
    }

    // Chat Message 相关订阅
    static requestSubscribeChat() {
        // #ifdef MP-WEIXIN
        PlatformUtils.requestSubscribeMessage(socialPlatformModule.wx_messageTemplateIds)
        // #endif
        // #ifdef MP-QQ
        PlatformUtils.requestSubscribeMessage(socialPlatformModule.qq_messageTemplateIds)
        // #endif
    }

    // Report相关订阅
    static requestSubscribeReport() {
        // #ifdef MP-WEIXIN
        PlatformUtils.requestSubscribeMessage(socialPlatformModule.wx_reportTemplateIds)
        // #endif
        // #ifdef MP-QQ
        PlatformUtils.requestSubscribeMessage(socialPlatformModule.qq_reportTemplateIds)
        // #endif
    }*/

    // 统一处理各平台的订阅
    static requestSubscribeMessage(tmplIds: string[]) {
        // #ifdef MP-WEIXIN
        // WxUtils.subscribeAppMsg(tmplIds)
        // #endif
        // #ifdef MP-QQ
        // QQUtils.subscribeAppMsg(tmplIds)
        // #endif
    }

    // 统一处理各平台的支付
    /*static userPay(amount?: number) {
        return PlatformUtils.payCoin(amount).then(() => {
            /!* UserStore.getMineUserAction().then(() => {
               AlertUtil.hint(HintMsg.paySuccessMsg)
               RouterUtil.reLaunch(UserPagePath.userMine)
             })*!/
        })
    }*/

    //所有只能直接调用这个
    /*static async payCoin(amount: number) {
        PlatformUtils.checkPay()
        //目前支持微信支付
        // const provider: string = SocialuniProviderType.wx
        return SocialuniCoinAPI.payCoinAPI(socialuniSystemModule.provider, amount).then((res) => {
            return PlatformUtils.cashPay(res.data)
        })
    }

    //所有只能直接调用这个
    static async checkPay() {
        console.log(`isProd:${socialuniSystemModule.isProd}`)
        if (!socialuniUserModule.mineUser) {
            return MsgUtil.unLoginMessage()
        } else if (socialuniSystemModule.isIos && socialuniSystemModule.isProd) {
            MsgUtil.iosDisablePay()
            throw '禁止支付功能'
        }
    }


    private static async cashPay(res: UserPayResultVO): Promise<any> {
        return PlatformUtils.requestPayment(res)
            .catch((err) => {
                // qq的取消支付没有走着里
                if (err.errMsg === Constants.wxPayCancel || err.errMsg === Constants.qqPayCancel || err.errMsg === Constants.appWxPayCancel) {
                    ToastUtil.toast(AppMsg.payCancelMsg)
                    throw err
                } else {
                    SocialuniAppAPI.sendErrorLogAPI(null, '支付失败', res, err)
                    // MsgUtil.payFailMsg()
                    throw err
                }
            })
    }

    //会员走payVip
    //会员走userPay
    //会员走cashPay
    //底层requestPayment处理平台差异。
    private static async requestPayment(payResult: UserPayResultVO) {
        if (socialuniSystemModule.isMpQQ) {
            return QQUtils.requestPayment(payResult)
        } else if (socialuniSystemModule.isMpWx || socialuniSystemModule.isApp) {
            return WxUtils.requestPayment(payResult)
        } else {
            throw '不存在的支付渠道'
        }
    }
*/
    static checkUpdate() {
        // #ifdef MP
        MPUtil.checkUpdate()
        // #endif
        // #ifdef APP-PLUS
        APPUtil.checkUpdate()
        // #endif
    }
}
