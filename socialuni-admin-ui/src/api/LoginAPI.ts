import http from '@/plugins/http'
import JsonUtil from '@/utils/JsonUtil'
import LoginUserVO from '@/model/base/LoginUserVO'
import EncryptUtil from '@/utils/EncryptUtil'

export default class LoginAPI {
  static loginAPI(user: LoginUserVO) {
    return http.post('user/login', user).then((res: any) => {
      return res.data
    })
  }

  static logoutAPI() {
    return http.post('user/logout').then((res: any) => {
      return res.data
    })
  }
}
