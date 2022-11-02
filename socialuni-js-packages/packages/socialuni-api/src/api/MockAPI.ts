import request from '../request/request'
import SocialLoginRO from '../model/social/SocialLoginRO'
import UniUserInfoRO from '@/socialuni/model/UniUserInfoRO'

export default class MockAPI {
    /*static mockOAuthUserInfoAPI () {
      return socialHttp.post<SocialLoginRO<UniUserInfoRO>>('mock/mockOAuthUserInfo')
    }*/

    static mockOAuthUserPhoneNumAPI() {
        return request.post<SocialLoginRO<UniUserInfoRO>>('mock/mockOAuthUserPhoneNum')
    }
}
