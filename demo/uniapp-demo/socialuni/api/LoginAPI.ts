import ProviderUserVO from '/socialuni/model/login/ProviderUserVO'
import socialHttp from '/socialuni/plugins/http/socialHttp'
import UniProviderLoginQO from '/socialuni/model/UniProviderLoginQO'
import SocialLoginRO from '/socialuni/model/social/SocialLoginRO'
import SocialPhoneNumLoginQO from '/socialuni/model/phone/SocialPhoneNumLoginQO'
import CenterUserDetailRO from '/socialuni/model/social/CenterUserDetailRO'


export default class LoginAPI {
  static providerLoginAPI (loginData: UniProviderLoginQO) {
    return socialHttp.post<SocialLoginRO<CenterUserDetailRO>>('login/providerLogin', loginData)
  }

  static phoneLoginAPI (loginData: SocialPhoneNumLoginQO) {
    return socialHttp.post<SocialLoginRO<CenterUserDetailRO>>('login/phoneLogin', loginData)
  }

  //微信绑定手机号使用
  static bindPhoneNumAPI (loginData: ProviderUserVO) {
    return socialHttp.post<CenterUserDetailRO>('user/bindPhoneNumByWx', loginData)
  }
}
