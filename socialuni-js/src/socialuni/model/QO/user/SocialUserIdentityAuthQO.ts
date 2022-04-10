export default class SocialUserIdentityAuthQO {
  public idImgUrl: string = null
  public selfieImgUrl: string = null

  constructor (idImgUrl: string, selfieImgUrl: string) {
    this.idImgUrl = idImgUrl
    this.selfieImgUrl = selfieImgUrl
  }
}
