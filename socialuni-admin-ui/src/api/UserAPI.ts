import http from '@/plugins/http'
import JsonUtil from '@/utils/JsonUtil'
import SocialPhoneNumQO from '@/model/base/SocialPhoneNumQO'
import EncryptUtil from '@/utils/EncryptUtil'

export default class UserAPI {
  static getUserAPI() {
    return http.post('user/getUser')
  }

  static resetSecretKeyAPI() {
    return http.post('user/resetSecretKey')
  }
}
