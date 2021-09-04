import ProviderUserVO from '@/model/login/ProviderUserVO'
import http from '@/plugins/http'
import UniProviderLoginQO from '@/model/UniProviderLoginQO'
import SocialLoginRO from '@/model/social/SocialLoginRO'
import SocialPhoneNumLoginQO from '@/model/phone/SocialPhoneNumLoginQO'
import CenterUserDetailRO from '@/model/social/CenterUserDetailRO'


export default class LoginAPI {
  static providerLoginAPI (loginData: UniProviderLoginQO) {
    return http.post<SocialLoginRO<CenterUserDetailRO>>('login/providerLogin', loginData)
  }

  static phoneLoginAPI (loginData: SocialPhoneNumLoginQO) {
    return http.post<SocialLoginRO<CenterUserDetailRO>>('login/phoneLogin', loginData)
  }

  //微信绑定手机号使用
  static bindPhoneNumAPI (loginData: ProviderUserVO) {
    return http.post<CenterUserDetailRO>('user/bindPhoneNumByWx', loginData)
  }
}
