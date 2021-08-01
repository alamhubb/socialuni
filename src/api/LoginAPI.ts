import ProviderUserVO from '@/model/login/ProviderUserVO'
import http from '@/plugins/http'
import UserVO from '@/model/user/UserVO'
import UniProviderLoginQO from '@/model/UniProviderLoginQO'
import SocialLoginRO from '@/model/social/SocialLoginRO'
import PhoneNumFormData from '@/model/phone/PhoneNumFormData'


export default class LoginAPI {
  static providerLoginAPI (loginData: UniProviderLoginQO) {
    return http.post<SocialLoginRO<UserVO>>('login/providerLogin', loginData)
  }

  static phoneLoginAPI (loginData: PhoneNumFormData) {
    return http.post<SocialLoginRO<UserVO>>('login/phoneLogin', loginData)
  }

  //微信绑定手机号使用
  static bindPhoneNumAPI (loginData: ProviderUserVO) {
    return http.post<UserVO>('user/bindPhoneNumByWx', loginData)
  }
}
