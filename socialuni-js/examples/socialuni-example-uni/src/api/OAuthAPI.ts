import OAuthUserInfoQO from "@socialuni/socialuni-api/src/model/dev/OAuthUserInfoQO";
import UniUserInfoRO from "@socialuni/socialuni-api/src/model/UniUserInfoRO";
import SocialLoginRO from "@socialuni/socialuni-api/src/model/social/SocialLoginRO";
import request from "@socialuni/socialuni-api/src/request/request";

export default class OAuthAPI {
  static oAuthUserInfoAndPhoneNumAPI(appId: string, provider: string) {
    const queryVO: OAuthAPI = new OAuthUserInfoQO(appId, provider)
    return request.post<SocialLoginRO<UniUserInfoRO>>('oAuth/oAuthUserInfoAndPhoneNum', queryVO)
  }
}
