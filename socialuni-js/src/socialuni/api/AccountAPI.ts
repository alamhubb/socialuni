import socialHttp from '../plugins/http/socialHttp'
import UniProviderLoginQO from '../model/UniProviderLoginQO'


export default class AccountAPI {
  static bindSocialuniAccountAPI (loginData: UniProviderLoginQO) {
    return socialHttp.post<void>('account/bindSocialuniAccount', loginData)
  }
}
