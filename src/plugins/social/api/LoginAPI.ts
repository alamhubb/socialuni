import LoginDataVO from '@/model/login/LoginDataVO'
import socialHttp from '@/plugins/social/http'
import ProviderUserVO from '@/plugins/uni/model/login/ProviderUserVO'

export default class LoginAPI {
  static providerLoginAPI (loginData: ProviderUserVO) {
    return socialHttp.post('user/providerLogin', loginData)
  }

  //微信绑定手机号使用
  static bindPhoneNumAPI (loginData: LoginDataVO) {
    return socialHttp.post('user/bindPhoneNum', loginData)
  }
}
