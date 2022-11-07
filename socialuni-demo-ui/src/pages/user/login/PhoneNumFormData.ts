import NumberUtil from "../socialuni-js-packages/packages/socialuni-use/src/utils/NumberUtil";

export default class PhoneNumFormData {
  phoneNum: string = ''
  authCode: string = null

  static phoneNumberError(phoneNum) {
    return !phoneNum || phoneNum.length !== 11 || NumberUtil.containNoNumber(phoneNum)
  }

  static authCodeError(authCode) {
    return !authCode || authCode.length !== 4 || NumberUtil.containNoNumber(authCode)
  }
}
