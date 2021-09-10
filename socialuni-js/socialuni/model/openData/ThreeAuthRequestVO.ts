import { socialAppModule, socialSystemModule } from '../../store'

export default class ThreeAuthRequestVO {
  threeSecretKey: string
  threeUserId?: string
  providerAppId: string
  providerType: string

  constructor () {
    this.threeSecretKey = socialAppModule.threeSecretKey
    this.threeUserId = socialAppModule.threeUserId
    this.providerAppId = socialAppModule.threeAppId
    this.providerType = socialSystemModule.mpPlatform
  }
}
