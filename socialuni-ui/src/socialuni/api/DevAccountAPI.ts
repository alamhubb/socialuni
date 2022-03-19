import DevAccountRO from '@/socialuni/model/dev/DevAccountRO'
import socialHttp from '@/socialuni/plugins/http/socialHttp'
import OAuthUserInfoQO from '@/socialuni/model/dev/OAuthUserInfoQO'

export default class DevAccountAPI {
  static queryDevAccountAPI (appId: string, provider: string) {
    const queryVO: OAuthUserInfoQO = new OAuthUserInfoQO(appId, provider)
    return socialHttp.post<DevAccountRO>('dev/queryDevAccount', queryVO)
  }
}
