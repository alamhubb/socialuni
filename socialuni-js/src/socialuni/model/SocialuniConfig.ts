import { SocialMpConfig } from './SocialMpConfig'
import GenderType from '../const/GenderType'
import { Store } from 'vuex'

export default class SocialuniConfig {
  //app的性别类型，全部性别，还是仅女生可用
  static readonly appGenderType = GenderType.all
  static readonly authApp = true
  static readonly socialuniHttpBaseUrl = 'https://api.qingchiapp.com'
  // static readonly devSocialuniHttpBaseUrl = 'https://devapi.qingchiapp.com'
  static readonly devSocialuniHttpBaseUrl = 'https://localpc.qingchiapp.com:8092'
  store: Store<any>
  //无后台项目，需要在前台此处配置秘钥，访问社交联盟使用。
  //授权登录时可选填，不填则使用后台绑定的appId进行判断，如未在开发者后台绑定appId，则需要填写秘钥
  secretKey?: string = null
  //无后台项目，借用社交联盟实现渠道登录时使用
  weixin?: SocialMpConfig = null
  //无后台项目，借用社交联盟实现渠道登录时使用
  qq?: SocialMpConfig = null

  constructor (socialConfig?: SocialuniConfig) {
    if (socialConfig) {
      this.secretKey = socialConfig.secretKey || null
      this.weixin = socialConfig.weixin || null
      this.qq = socialConfig.qq || null
    }
  }
}
