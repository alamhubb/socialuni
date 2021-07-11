import socialHttp from '@/plugins/social/http'
import CosAuthRO from '@/model/cos/CosAuthRO'

export default class CosAPI {
  static getCosAuthorizationAPI () {
    return socialHttp.post<CosAuthRO>('cos/getCosAuthorization')
  }
}
