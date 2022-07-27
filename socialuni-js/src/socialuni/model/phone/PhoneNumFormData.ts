import NumberUtil from '../../utils/NumberUtil'

export default class PhoneNumFormData {
  phoneNum: string = '18600409559'
  authCode: string = null

  static phoneNumberError(phoneNum) {
    return !phoneNum || phoneNum.length !== 11 || NumberUtil.containNoNumber(phoneNum)
  }

  static authCodeError(authCode) {
    return !authCode || authCode.length !== 4 || NumberUtil.containNoNumber(authCode)
  }
}
