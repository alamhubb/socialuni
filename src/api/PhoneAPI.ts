import http from '@/plugins/http'
import SocialUserRO from '@/model/social/SocialUserRO'

export default class PhoneAPI {
  static bindSocialPhoneNum () {
    return http.post<SocialUserRO>('phone/bindSocialPhoneNum')
  }
}
