import { appModule, socialSystemModule } from '/socialuni/store'

export default class ThreeAuthRequestVO {
  threeSecretKey: string
  threeUserId?: string
  providerAppId: string
  providerType: string

  constructor () {
    this.threeSecretKey = appModule.threeSecretKey
    this.threeUserId = appModule.threeUserId
    this.providerAppId = appModule.threeAppId
    this.providerType = socialSystemModule.mpPlatform
  }
}
