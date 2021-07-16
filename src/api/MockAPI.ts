import http from '@/plugins/http'
import SocialLoginRO from '@/model/social/SocialLoginRO'
import SocialUserRO from '@/model/social/SocialUserRO'

export default class MockAPI {
  static mockOAuthUserInfoAPI () {
    return http.post<SocialLoginRO<SocialUserRO>>('mock/mockOAuthUserInfo')
  }

  static mockOAuthUserPhoneNumAPI () {
    return http.post<SocialLoginRO<SocialUserRO>>('mock/mockOAuthUserPhoneNum')
  }
}
