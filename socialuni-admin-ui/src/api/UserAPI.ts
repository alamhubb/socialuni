import http from '@/plugins/http'
import JsonUtil from '@/utils/JsonUtil'
import LoginUserVO from '@/model/base/LoginUserVO'
import EncryptUtil from '@/utils/EncryptUtil'

export default class UserAPI {
  static getUserAPI() {
    return http.post('user/getUser')
  }

  static resetSecretKeyAPI() {
    return http.post('user/resetSecretKey')
  }
}
