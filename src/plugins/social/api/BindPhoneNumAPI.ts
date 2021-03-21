import ProviderUserVO from '@/plugins/uni/model/login/ProviderUserVO'
import socialHttp from '@/plugins/social/http'
import UserVO from '@/model/user/UserVO'


export default class BindPhoneNumAPI {
  //微信绑定手机号使用
  static bindPhoneNumByWxAPI (loginData: ProviderUserVO) {
    return socialHttp.post<UserVO>('user/bindPhoneNumByWx', loginData)
  }
}
