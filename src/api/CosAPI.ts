import http from '@/plugins/http'

export default class CosAPI {
  static getCosAuthorizationAPI (userId) {
    return http.post('cos/getCosAuthorization', {
      userId
    })
  }
}
