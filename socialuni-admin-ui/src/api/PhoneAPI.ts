import request from '@/plugins/request'

export default class PhoneAPI {
  static sendAuthCodeAPI(phoneNum: string) {
    return request.post('phone/sendAuthCode', { phoneNum })
  }
}
