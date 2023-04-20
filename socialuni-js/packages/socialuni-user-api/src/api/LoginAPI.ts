import socialuniAppRequest from "socialuni/src/request/socialuniAppRequest";
import UniProviderLoginQO from "socialuni/src/model/login/UniProviderLoginQO";
import SocialLoginRO from "socialuni/src/model/social/SocialLoginRO";
import SocialuniMineUserRO from "socialuni/src/model/user/SocialuniMineUserRO";
import SocialPhoneNumLoginQO from "socialuni/src/model/phone/SocialPhoneNumLoginQO";

export default class LoginAPI {
  static providerLoginAPI () {
    return socialuniAppRequest.post('socialuni/login/providerLogin')
  }

  static socialuniPhoneLoginAPI (loginData: UniProviderLoginQO) {
    //接口已作废
    return socialuniAppRequest.post<SocialLoginRO<SocialuniMineUserRO>>('socialuni/login/socialuniPhoneLogin', loginData)
  }

  static phoneLoginAPI (loginData: SocialPhoneNumLoginQO) {
    return socialuniAppRequest.post<SocialLoginRO<SocialuniMineUserRO>>('socialuni/login/phoneLogin', loginData)
  }

  /**
   * 刷新token
   * @param loginData
   */
  static refreshToken () {
    return socialuniAppRequest.post<SocialLoginRO<SocialuniMineUserRO>>('socialuni/login/refreshToken', {});
  }
}
