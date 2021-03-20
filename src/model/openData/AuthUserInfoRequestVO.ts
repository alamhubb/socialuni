import { appModule, systemModule } from '@/plugins/store'

export default class AuthUserInfoRequestVO {
  threeSecretKey: string
  threeUserId?: string
  providerAppId: string
  providerType: string

  constructor () {
    this.threeSecretKey = appModule.threeSecretKey
    this.threeUserId = appModule.threeUserId
    this.providerAppId = appModule.threeAppId
    this.providerType = systemModule.provider
  }
}
