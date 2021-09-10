export default class ErrorConst {
  // 成功
  static readonly success: number = 0
  // 错误码
  // 未登录错误
  static readonly not_logged: number = 601
  // 业务错误
  static readonly business: number = 602
  // 自定义错误
  static readonly custom: number = 603
  // 系统异常
  static readonly system: number = 604
  // 封号异常
  static readonly banned: number = 605

  // 系统提示
  static readonly systemError601Key: string = 'systemError601'
  static readonly systemError601Msg: string = '未登录，是否跳转至登录页'

  static readonly systemError604Key: string = 'systemError604'
  static readonly systemError604Msg: string = '系统出错，有任何疑问请联系客服qq或微信：491369310'

  static readonly systemError605Key: string = 'systemError605'
  static readonly systemError605Msg: string = '账号违规，被封禁，有任何疑问，联系客服微博：清池恋爱交友app，微信或qq:491369310'
}
