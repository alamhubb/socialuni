import request from '../../plugins/http/request'
import UniProviderLoginQO from '../../model/UniProviderLoginQO'
import SocialLoginRO from '../../model/social/SocialLoginRO'
import SocialPhoneNumLoginQO from '../../model/phone/SocialPhoneNumLoginQO'
import CenterUserDetailRO from '../../model/social/CenterUserDetailRO'


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
}
