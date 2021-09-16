import socialHttp from '../plugins/http/socialHttp'
import SocialLoginRO from '../model/social/SocialLoginRO'
import UniUserInfoRO from '@/socialuni/model/UniUserInfoRO'

export default class MockAPI {
  static mockOAuthUserInfoAPI () {
    return socialHttp.post<SocialLoginRO<UniUserInfoRO>>('mock/mockOAuthUserInfo')
  }

  static mockOAuthUserPhoneNumAPI () {
    return socialHttp.post<SocialLoginRO<UniUserInfoRO>>('mock/mockOAuthUserPhoneNum')
  }
}
