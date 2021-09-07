import { socialSystemModule } from '@/store'
import { socialConfig } from '@/socialuni'
import UniUserInfoRO from '@/model/UniUserInfoRO'
import UniProviderType from '@/const/UniProviderType'
import GetUserInfoRes = UniApp.GetUserInfoRes
import PlatformType from '@/const/PlatformType'

export default class UniProviderLoginQO {
  provider = ''
  platform = ''

  //小程序三方登录使用
  code = ''

  openId = ''
  unionId = ''

  //微信mp获取unionId使用
  encryptedData = ''
  iv = ''

  //微信有活在梦里
  nickName = ''
  //微信有url
  avatarUrl = ''

  //微信有1,2
  gender: number = 0
  //qq会给生日
  birthday = ''


  //微信有China
  country = ''
  //微信有Beijing
  province = ''
  //微信有Chaoyang
  city = ''

  appId = ''
  secret = ''

  constructor (providerCode: string, userInfoRes: GetUserInfoRes, provider?: string) {
    this.platform = socialSystemModule.platform
    //如果为小程序的话不传值，默认为小程序类型
    this.provider = provider || socialSystemModule.mpPlatform

    if (this.platform === PlatformType.mp) {
      if (this.provider === UniProviderType.wx) {
        this.appId = socialConfig.weixin.mpId
        this.secret = socialConfig.weixin.mpSecret
      } else {
        throw new Error('暂不支持的渠道')
      }
    } else {
      throw new Error('暂不支持的平台')
    }

    this.code = providerCode || ''

    this.iv = userInfoRes.iv || ''
    this.encryptedData = userInfoRes.encryptedData || ''

    const userInfoRO: UniUserInfoRO = userInfoRes.userInfo as UniUserInfoRO
    this.openId = userInfoRO.openId || ''
    this.unionId = userInfoRO.unionId || ''

    this.nickName = userInfoRO.nickName || ''
    this.avatarUrl = userInfoRO.avatarUrl || ''
    this.gender = userInfoRO.gender || 0
    this.country = userInfoRO.country || ''
    this.province = userInfoRO.province || ''
    this.city = userInfoRO.city || ''

    if (socialSystemModule.isApp) {
      if (provider === UniProviderType.qq) {
        this.gender = userInfoRO.gender_type || 0
      }
      //如果qq可以获取用户年龄
      this.birthday = userInfoRO.year || ''
    }
  }
}
