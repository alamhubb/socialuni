export default class OAuthUserInfoQO {
  appId: string
  mpType: string

  constructor (appId: string, mpType: string) {
    this.appId = appId
    this.mpType = mpType
  }
}
