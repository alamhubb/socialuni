import socialHttp from '/socialuni/plugins/http/socialHttp'
import CosAuthRO from '/socialuni/model/cos/CosAuthRO'

export default class CosAPI {
  static getCosAuthorizationAPI () {
    return socialHttp.post<CosAuthRO>('cos/getCosAuth')
  }
}
