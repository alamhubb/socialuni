export default class BindPhoneQO {
  phoneNum: string = null
  authCode: string = null

  constructor (phoneNum: string = null, authCode: string = null) {
    this.phoneNum = phoneNum
    this.authCode = authCode
  }
}
