import request from '../../plugins/http/request'
import CosAuthRO from '../../model/cos/CosAuthRO'

export default class SocialuniCosAPI {
  static getCosAuthorizationAPI () {
    return request.post<CosAuthRO>('socialuni/cos/getCosAuth')
  }
}
