import socialHttp from '@/plugins/social/http'
import SocialUserRO from '@/plugins/social/model/SocialUserRO'

export default class PhoneAPI {
  static bindSocialPhoneNum () {
    return socialHttp.post<SocialUserRO>('phone/bindSocialPhoneNum')
  }
}
