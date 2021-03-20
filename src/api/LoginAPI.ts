import ProviderUserVO from '@/plugins/uni/model/login/ProviderUserVO'
import http from '@/plugins/http'
import { systemModule } from '@/plugins/store'
import UserStoreCom from '@/plugins/store/UserStoreCom'

export default class LoginAPI {
  static platformLoginAPI (loginData: ProviderUserVO) {
    if (systemModule.isApp) {
      loginData.clientid = systemModule.clientid
    }
    return http.post('user/platformLogin', loginData).then((res: any) => {
      UserStoreCom.getMineUserInitDataActionByToken(res)
      return res.data.user
    })
  }

  //微信绑定手机号使用
  static bindPhoneNumAPI (loginData: ProviderUserVO) {
    return http.post('user/bindPhoneNum', loginData)
  }
}
