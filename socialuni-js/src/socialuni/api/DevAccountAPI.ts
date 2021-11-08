import DevAccountRO from '@/socialuni/model/dev/DevAccountRO'
import OAuthAPI from '@/api/OAuthAPI'
import OAuthUserInfoQO from '@/model/dev/OAuthUserInfoQO'
import socialHttp from '@/socialuni/plugins/http/socialHttp'

export default class DevAccountAPI {
  static queryDevAccountAPI (appId: string, provider: string) {
    const queryVO: OAuthAPI = new OAuthUserInfoQO(appId, provider)
    return socialHttp.post<DevAccountRO>('dev/queryDevAccount', queryVO)
  }
}
