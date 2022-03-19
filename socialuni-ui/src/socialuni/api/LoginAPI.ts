import socialHttp from '../plugins/http/socialHttp'
import UniProviderLoginQO from '../model/UniProviderLoginQO'
import SocialLoginRO from '../model/social/SocialLoginRO'
import SocialPhoneNumLoginQO from '../model/phone/SocialPhoneNumLoginQO'
import CenterUserDetailRO from '../model/social/CenterUserDetailRO'


export default class LoginAPI {
  static providerLoginAPI (loginData: UniProviderLoginQO) {
    return socialHttp.post<SocialLoginRO<CenterUserDetailRO>>('login/providerLogin', loginData)
  }

  static socialuniPhoneLoginAPI (loginData: UniProviderLoginQO) {
    return socialHttp.post<SocialLoginRO<CenterUserDetailRO>>('login/socialuniPhoneLogin', loginData)
  }

  static phoneLoginAPI (loginData: SocialPhoneNumLoginQO) {
    return socialHttp.post<SocialLoginRO<CenterUserDetailRO>>('login/phoneLogin', loginData)
  }
}
