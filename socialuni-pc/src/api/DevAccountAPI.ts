import DevAccountRO from '@/socialuni/model/dev/DevAccountRO'
import request from '../plugins/http/request'
import OAuthUserInfoQO from '@/socialuni/model/dev/OAuthUserInfoQO'

export default class DevAccountAPI {
  static queryDevAccountAPI (appId: string, provider: string) {
    const queryVO: OAuthUserInfoQO = new OAuthUserInfoQO(appId, provider)
    return request.post<DevAccountRO>('dev/queryDevAccount', queryVO)
  }
}
