import request from "@socialuni/socialuni-api/src/request/request";
import UniUserInfoRO from "@socialuni/socialuni-api/src/model/UniUserInfoRO";
import SocialLoginRO from "@socialuni/socialuni-api/src/model/social/SocialLoginRO";

export default class MockAPI {
    /*static mockOAuthUserInfoAPI () {
      return socialHttp.post<SocialLoginRO<UniUserInfoRO>>('mock/mockOAuthUserInfo')
    }*/

    static mockOAuthUserPhoneNumAPI() {
        return request.post<SocialLoginRO<UniUserInfoRO>>('mock/mockOAuthUserPhoneNum')
  }
}
