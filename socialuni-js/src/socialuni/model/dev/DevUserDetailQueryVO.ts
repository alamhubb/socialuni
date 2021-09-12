export default class DevUserDetailQueryVO {
  threeSecretKey: string = null
  threeAppId: string = null

  constructor (threeSecretKey: string, threeAppId: string) {
    this.threeSecretKey = threeSecretKey
    this.threeAppId = threeAppId
  }
}
