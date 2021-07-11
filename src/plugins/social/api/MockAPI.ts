import socialHttp from '@/plugins/social/http'
import ComLoginRO from '@/plugins/social/model/ComLoginRO'
import SocialUserRO from '@/plugins/social/model/SocialUserRO'

export default class MockAPI {
  static mockOAuthUserInfoAPI () {
    return socialHttp.post<ComLoginRO<SocialUserRO>>('mock/mockOAuthUserInfo')
  }

  static mockOAuthUserPhoneNumAPI () {
    return socialHttp.post<ComLoginRO<SocialUserRO>>('mock/mockOAuthUserPhoneNum')
  }
}
