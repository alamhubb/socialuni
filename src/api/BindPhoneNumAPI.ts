import ProviderUserVO from '@/model/ProviderUserVO'
import http from '@/plugins/http'
import UserVO from '@/model/user/UserVO'


export default class BindPhoneNumAPI {
  //微信绑定手机号使用
  static bindPhoneNumByWxAPI (loginData: ProviderUserVO) {
    return http.post<UserVO>('user/bindPhoneNumByWx', loginData)
  }
}
