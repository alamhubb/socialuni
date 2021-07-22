import NumberUtil from '@/utils/NumberUtil'

export default class PhoneFormData {
  phoneNum: string = null
  authCode: string = null

  constructor (phoneNum: string = null, authCode: string = null) {
    this.phoneNum = phoneNum
    this.authCode = authCode
  }

  get authCodeRight () {
    return this.authCode && this.authCode.length === 4 && NumberUtil.isAllNumber(this.authCode)
  }

  get phoneNumberRight () {
    return this.phoneNum && this.phoneNum.length === 11 && NumberUtil.isAllNumber(this.phoneNum)
  }
}
