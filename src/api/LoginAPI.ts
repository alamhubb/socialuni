import ProviderUserVO from '@/model/ProviderUserVO'
import http from '@/plugins/http'
import UserVO from '@/model/user/UserVO'
import UniProviderLoginQO from '@/model/UniProviderLoginQO'
import SocialLoginRO from '@/model/social/SocialLoginRO'
import PhoneFormData from '@/model/login/PhoneFormData'


export default class LoginAPI {
  static providerLoginAPI (loginData: UniProviderLoginQO) {
    return http.post<SocialLoginRO<UserVO>>('user/providerLogin', loginData)
  }

  static phoneLoginAPI (loginData: PhoneFormData) {
    return http.post<SocialLoginRO<UserVO>>('user/phoneLogin', loginData)
  }

  //微信绑定手机号使用
  static bindPhoneNumAPI (loginData: ProviderUserVO) {
    return http.post<UserVO>('user/bindPhoneNumByWx', loginData)
  }
}
