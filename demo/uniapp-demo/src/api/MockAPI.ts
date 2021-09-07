import socialHttp from '@/socialuni/http/socialHttp'
import SocialLoginRO from '@/model/social/SocialLoginRO'
import CenterUserDetailRO from '@/model/social/CenterUserDetailRO'

export default class MockAPI {
  static mockOAuthUserInfoAPI () {
    return socialHttp.post<SocialLoginRO<CenterUserDetailRO>>('mock/mockOAuthUserInfo')
  }

  static mockOAuthUserPhoneNumAPI () {
    return socialHttp.post<SocialLoginRO<CenterUserDetailRO>>('mock/mockOAuthUserPhoneNum')
  }
}
