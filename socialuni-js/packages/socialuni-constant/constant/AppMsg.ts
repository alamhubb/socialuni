export default class AppMsg {
  static readonly payOpenDefaultMsg: string = '我开起了和您的会话'

  static readonly iosDisablePayMsg: string = '由于ios相关规则限制，ios系统被禁止支持此类付费功能'
  static readonly notMpDisablePayMsg: string = '非小程序平台暂不支持此类支付功能'
  static readonly notPayMsg: string = '因平台政策原因暂不支持支付功能'

  static readonly contactServiceMsg: string = '，请联系客服微信'

  // static readonly vipOpenFailMsg: string = '开通会员失败' + AppMsg.contactServiceMsg

  static readonly payFailMsg: string = '充值失败'

  static readonly payCancelMsg: string = '您取消了充值'

  static readonly paySuccessMsg: string = '充值成功'

  static readonly uploadFailMsg: string = '照片上传失败，请重试或联系客服'

  static readonly identityAuthSuccessMsg: string = '认证成功，是否回到个人信息页面'

  static readonly imgSizeNotGt10MBMsg: string = '图片大小不能超过10MB！'
  static readonly imgSizeNotGt50MBMsg: string = '图片大小不能超过50MB！'
}
