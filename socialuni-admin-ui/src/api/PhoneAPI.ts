import http from '@/plugins/http'

export default class PhoneAPI {
  static sendAuthCodeAPI(phoneNum: string) {
    return http.post('phone/sendAuthCode', { phoneNum })
  }
}
