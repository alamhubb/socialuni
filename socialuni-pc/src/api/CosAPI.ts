import request from '../plugins/http/request'
import CosAuthRO from '../model/cos/CosAuthRO'

export default class CosAPI {
  static getCosAuthorizationAPI () {
    return request.post<CosAuthRO>('cos/getCosAuth')
  }
}
