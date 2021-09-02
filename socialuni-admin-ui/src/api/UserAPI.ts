import request from '@/plugins/request'

export default class UserAPI {
  static getUserAPI() {
    return request.post('user/getUser')
  }

  static resetSecretKeyAPI() {
    return request.post('user/resetSecretKey')
  }
}
