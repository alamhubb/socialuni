import socialHttp from '@/plugins/social/http'
import UserVO from '@/model/user/UserVO'
import UniProviderLoginQO from '@/plugins/uniapp-sdk/model/UniProviderLoginQO'


export default class BindPhoneNumAPI {
  //微信绑定手机号使用
  static bindPhoneNumByWxAPI (loginData: UniProviderLoginQO) {
    return socialHttp.post<UserVO>('user/bindPhoneNumByWx', loginData)
  }
}
