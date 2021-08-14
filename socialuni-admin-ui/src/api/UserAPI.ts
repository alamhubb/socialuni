import http from '@/plugins/http'

export default class UserAPI {
  static getUserAPI() {
    return http.post('user/getUser')
  }

  static resetSecretKeyAPI() {
    return http.post('user/resetSecretKey')
  }
}
