import socialHttp from '@/plugins/social/http'
import ProviderUserVO from '@/model/login/ProviderUserVO'
import UserVO from '@/model/user/UserVO'
import UniProviderLoginQO from '@/plugins/uniapp-sdk/model/UniProviderLoginQO'
import ComLoginRO from '@/plugins/social/model/ComLoginRO'
import ComUserRO from '@/plugins/social/model/ComUserRO'

export default class LoginAPI {
  static providerLoginAPI (data: UniProviderLoginQO) {
    return socialHttp.post<ComLoginRO<ComUserRO>>('login/providerLogin', data)
  }

  static mockLogin () {
    return socialHttp.post('social/mockSocialLogin')
  }

  static mockBindPhone () {
    return socialHttp.post<UserVO>('social/mockSocialBindPhone')
  }


  //微信绑定手机号使用
  static bindPhoneNumAPI (loginData: ProviderUserVO) {
    return socialHttp.post<UserVO>('user/bindPhoneNumByWx', loginData)
  }
}
