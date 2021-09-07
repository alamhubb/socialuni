import socialHttp from '@/socialuni/http/socialHttp'
import CosAuthRO from '@/model/cos/CosAuthRO'

export default class CosAPI {
  static getCosAuthorizationAPI () {
    return socialHttp.post<CosAuthRO>('cos/getCosAuth')
  }
}
