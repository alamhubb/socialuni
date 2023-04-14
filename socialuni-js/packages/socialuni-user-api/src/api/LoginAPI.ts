import socialuniAppRequest from "socialuni-base-api/src/request/socialuniAppRequest";
import UniProviderLoginQO from "socialuni-base-api/src/model/login/UniProviderLoginQO";
import SocialLoginRO from "socialuni-base-api/src/model/social/SocialLoginRO";
import SocialuniMineUserRO from "socialuni-base-api/src/model/user/SocialuniMineUserRO";
import SocialPhoneNumLoginQO from "socialuni-base-api/src/model/phone/SocialPhoneNumLoginQO";


export default class LoginAPI {
  static providerLoginAPI (loginData: UniProviderLoginQO) {
    return socialuniAppRequest.post<SocialLoginRO<SocialuniMineUserRO>>('socialuni/login/providerLogin', loginData)
  }

  /*static socialuniPhoneLoginAPI (loginData: UniProviderLoginQO) {
    //接口已作废
    return socialuniAppRequest.post<SocialLoginRO<SocialuniMineUserRO>>('socialuni/login/socialuniPhoneLogin', loginData)
  }

  static phoneLoginAPI (loginData: SocialPhoneNumLoginQO) {
    return socialuniAppRequest.post<SocialLoginRO<SocialuniMineUserRO>>('socialuni/login/phoneLogin', loginData)
  }

  /!**
   * 刷新token
   * @param loginData
   *!/
  static refreshToken () {
    return socialuniAppRequest.post<SocialLoginRO<SocialuniMineUserRO>>('socialuni/login/refreshToken', {});
  }*/
}
