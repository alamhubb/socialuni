export default class MpPlatformType {
  static readonly wx: wx = 'weixin'
  static readonly qq: qq = 'qq'
}

export type wx = 'weixin'
export type qq = 'qq'

export type MpPlatform = wx | qq
