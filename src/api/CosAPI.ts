import http from '@/plugins/http'
import CosAuthRO from '@/model/cos/CosAuthRO'

export default class CosAPI {
  static getCosAuthorizationAPI () {
    return http.post<CosAuthRO>('cos/getCosAuthorization')
  }
}
