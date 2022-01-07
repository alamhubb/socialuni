import socialHttp from '../socialuni/plugins/http/socialHttp'
import SocialLoginRO from '../socialuni/model/social/SocialLoginRO'
import UniUserInfoRO from '@/socialuni/model/UniUserInfoRO'
import OAuthUserInfoQO from '@/socialuni/model/dev/OAuthUserInfoQO'

export default class OAuthAPI {
  static oAuthUserInfoAndPhoneNumAPI (appId: string, provider: string) {
    const queryVO: OAuthAPI = new OAuthUserInfoQO(appId, provider)
    return socialHttp.post<SocialLoginRO<UniUserInfoRO>>('oAuth/oAuthUserInfoAndPhoneNum', queryVO)
  }
}
