import SocialConfig from '@/config/SocialConfig'

export default class SocialUniAuthVO {
  authType: string
  appSecretKey: string
  appUserId: string
  // appId: string


  //用户id选填，不填将联盟生成并返回
  constructor (authType: string, appUserId?: string) {
    this.authType = authType
    this.appSecretKey = SocialConfig.socialSecretKey
    this.appUserId = appUserId
  }
}
