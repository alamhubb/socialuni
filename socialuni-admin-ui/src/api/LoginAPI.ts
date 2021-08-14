import http from '@/plugins/http'
import SocialPhoneNumQO from '@/model/base/SocialPhoneNumQO'
import SocialLoginRO from '@/model/base/SocialLoginRO'

export default class LoginAPI {
  static loginAPI(user: SocialPhoneNumQO) {
    return http.post<SocialLoginRO>('user/phoneLogin', user).then(res => {
      return res.data
    })
  }

  static logoutAPI() {
    return http.post('user/logout').then((res: any) => {
      return res.data
    })
  }
}
