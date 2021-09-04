import http from '@/plugins/http'
import SocialLoginRO from '@/model/social/SocialLoginRO'
import CenterUserDetailRO from '@/model/social/CenterUserDetailRO'

export default class MockAPI {
  static mockOAuthUserInfoAPI () {
    return http.post<SocialLoginRO<CenterUserDetailRO>>('mock/mockOAuthUserInfo')
  }

  static mockOAuthUserPhoneNumAPI () {
    return http.post<SocialLoginRO<CenterUserDetailRO>>('mock/mockOAuthUserPhoneNum')
  }
}
