import ProviderUserVO from '@/model/login/ProviderUserVO'
import http from '@/plugins/http'
import UserVO from '@/model/user/UserVO'
import UniProviderLoginQO from '@/model/UniProviderLoginQO'
import SocialLoginRO from '@/model/social/SocialLoginRO'
import PhoneNumQO from '@/model/phone/PhoneNumQO'


export default class LoginAPI {
  static providerLoginAPI (loginData: UniProviderLoginQO) {
    return http.post<SocialLoginRO<UserVO>>('user/providerLogin', loginData)
  }

  static phoneLoginAPI (loginData: PhoneNumQO) {
    return http.post<SocialLoginRO<UserVO>>('user/phoneLogin', loginData)
  }

  //微信绑定手机号使用
  static bindPhoneNumAPI (loginData: ProviderUserVO) {
    return http.post<UserVO>('user/bindPhoneNumByWx', loginData)
  }
}
