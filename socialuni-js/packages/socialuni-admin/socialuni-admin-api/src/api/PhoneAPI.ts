import socialuniAdminRequest from '../socialuniAdminRequest'

export default class PhoneAPI {
  static sendAuthCodeAPI(phoneNum: string) {
    return socialuniAdminRequest.post('phone/sendAuthCode', { phoneNum })
  }
}
