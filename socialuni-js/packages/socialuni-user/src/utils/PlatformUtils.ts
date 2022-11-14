import QQUtils from './QQUtils'
import { socialPlatformModule } from "socialuni-sdk/src/store/store"
import { socialSystemModule } from "socialuni-user/src/store/store"
import { socialUserModule } from "socialuni-user/src/store/store"
import MsgUtil from "./MsgUtil"
import Constants from "socialuni-constant/constant/Constant"
import AppMsg from "socialuni-constant/constant/AppMsg"
import ToastUtil from "socialuni-use/src/utils/ToastUtil"
import SocialuniAppAPI from "socialuni-api/src/api/socialuni/SocialuniAppAPI"
import WxUtils from "./WxUtils"
import MPUtil from "socialuni-use/src/utils/MPUtil"
import APPUtil from "./APPUtil"
import SocialuniAppNewAPI from "../api/SocialuniAppNewAPI";
import UserPayResultVO from "../model/user/UserPayResultVO";


// 统一处理各平台的订阅
export default class PlatformUtils {
  // talk相关订阅
  static requestSubscribeTalk() {
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
  }

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
  static userPay(provider: string, payType: string, amount?: number) {
    MsgUtil.notPay()
    /*return PlatformUtils.pay(provider, payType, amount).then(() => {
      UserStore.getMineUserAction().then(() => {
        AlertUtil.hint(HintMsg.paySuccessMsg)
        RouterUtil.reLaunch(PagePath.userMine)
      })
    })*/
  }

  //所有只能直接调用这个
  static async pay(provider: string, payType: string, amount?: number) {
    if (!socialUserModule.user) {
      return MsgUtil.unLoginMessage()
    } else if (socialSystemModule.isIos) {
      MsgUtil.iosDisablePay()
      throw ''
    }
    throw ''
    /*return UserAPI.userPayAPI(provider, payType, amount).then((res) => {
      return PlatformUtils.cashPay(res.data)
    })*/
  }

  private static async cashPay(res: UserPayResultVO): Promise<any> {
    return PlatformUtils.requestPayment(res)
      .catch((err) => {
        // qq的取消支付没有走着里
        if (err.errMsg === Constants.wxPayCancel || err.errMsg === Constants.qqPayCancel || err.errMsg === Constants.appWxPayCancel) {
          ToastUtil.toast(AppMsg.payCancelMsg)
          throw err
        } else {
          SocialuniAppNewAPI.sendErrorLogAPI(null, '支付失败', res, err)
          MsgUtil.payFailMsg()
          throw err
        }
      })
  }

  //会员走payVip
  //会员走userPay
  //会员走cashPay
  //底层requestPayment处理平台差异。
  private static async requestPayment(payResult: UserPayResultVO) {
    if (socialSystemModule.isMpQQ) {
      return QQUtils.requestPayment(payResult)
    } else if (socialSystemModule.isMpWx || socialSystemModule.isApp) {
      return WxUtils.requestPayment(payResult)
    } else {
      throw '不存在的支付渠道'
    }
  }

  static checkUpdate() {
    // #ifdef MP
    MPUtil.checkUpdate()
    // #endif
    // #ifdef APP-PLUS
    APPUtil.checkUpdate()
    // #endif
  }
}
