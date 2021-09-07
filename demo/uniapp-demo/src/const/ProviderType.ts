export default class ProviderType {
  static readonly wx: wx = 'weixin'
  static readonly qq: qq = 'qq'
}

export type wx = 'weixin'
export type qq = 'qq'

export type Provider = wx | qq
