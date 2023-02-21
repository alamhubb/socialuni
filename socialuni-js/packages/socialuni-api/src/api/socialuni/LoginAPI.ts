import SocialLoginRO from '../../model/social/SocialLoginRO'
import SocialPhoneNumLoginQO from '../../model/phone/SocialPhoneNumLoginQO'
import CenterUserDetailRO from '../../model/social/CenterUserDetailRO'
import request from '../../request/request';
import UniProviderLoginQO from "../../model/login/UniProviderLoginQO";


export default class LoginAPI {
  static providerLoginAPI (loginData: UniProviderLoginQO) {
    return request.post<SocialLoginRO<CenterUserDetailRO>>('socialuni/login/providerLogin', loginData)
  }

  static socialuniPhoneLoginAPI (loginData: UniProviderLoginQO) {
    //接口已作废
    return request.post<SocialLoginRO<CenterUserDetailRO>>('socialuni/login/socialuniPhoneLogin', loginData)
  }

  static phoneLoginAPI (loginData: SocialPhoneNumLoginQO) {
    return request.post<SocialLoginRO<CenterUserDetailRO>>('socialuni/login/phoneLogin', loginData)
  }

  /**
   * 刷新token
   * @param loginData
   */
  static refreshToken () {
    return request.post<SocialLoginRO<CenterUserDetailRO>>('socialuni/login/refreshToken', {});
  }
}
