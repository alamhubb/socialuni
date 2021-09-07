import socialHttp from '/socialuni/plugins/http/socialHttp'
import SocialLoginRO from '/socialuni/model/social/SocialLoginRO'
import CenterUserDetailRO from '/socialuni/model/social/CenterUserDetailRO'

export default class MockAPI {
  static mockOAuthUserInfoAPI () {
    return socialHttp.post<SocialLoginRO<CenterUserDetailRO>>('mock/mockOAuthUserInfo')
  }

  static mockOAuthUserPhoneNumAPI () {
    return socialHttp.post<SocialLoginRO<CenterUserDetailRO>>('mock/mockOAuthUserPhoneNum')
  }
}
