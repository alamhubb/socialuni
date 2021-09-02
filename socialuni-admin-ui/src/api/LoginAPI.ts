import request from '@/plugins/request'
import SocialPhoneNumQO from '@/model/base/SocialPhoneNumQO'
import SocialLoginRO from '@/model/base/SocialLoginRO'
import UserVO from '@/model/base/UserVO'

export default class LoginAPI {
  static loginAPI(user: SocialPhoneNumQO) {
    return request.post<SocialLoginRO<UserVO>>('user/phoneLogin', user).then(res => {
      return res.data
    })
  }

  static logoutAPI() {
    return request.post('user/logout').then((res: any) => {
      return res.data
    })
  }
}
