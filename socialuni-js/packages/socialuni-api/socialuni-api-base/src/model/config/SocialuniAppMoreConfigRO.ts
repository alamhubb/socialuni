export default class SocialuniAppMoreConfigRO {
// 会员价格
  vipPrice: number = null
  // 首页swiper高度
  swiperHeight: number = null
  // 首页
  homeUrl: string = null
  // 建议
  suggestUrl: string = null
  //联系我们
  contactUsUrl: string = null
  //用户协议
  userAgreementUrl: string = null
  //用户隐私
  userPrivacyUrl: string = null
  //儿童保护协议
  childProtectUrl: string = null
  serviceWeChat: string = null
  //激励视频限制次数
  rewardedAdLimit: number = null
  //获取联系方式消耗多少金币
  contactExpenseShell: number = null
  //用户获得金币百分比
  contactUserReceiveShell: number = null
  //验证码间隔多少秒
  authCodeInterval: number = null
  //被举报几次隐藏
  reportCountHide: number = null
  //展示轮播图
  showSwipers: boolean = true

  //系统异常错误消息
  errorMsg601UnLogin: string = "未登录，请进行登录，是否前往登录"
  errorMsg604SystemError: string = "系统异常，有任何疑问请联系客服"
  errorMsgContactService: string = "有任何疑问请联系客服"
}
