import request from '../socialuni/plugins/http/request'
import SocialLoginRO from '../socialuni/model/social/SocialLoginRO'
import UniUserInfoRO from '@/socialuni/model/UniUserInfoRO'
import OAuthUserInfoQO from '@/socialuni/model/dev/OAuthUserInfoQO'

export default class OAuthAPI {
  static oAuthUserInfoAndPhoneNumAPI (appId: string, provider: string) {
    const queryVO: OAuthAPI = new OAuthUserInfoQO(appId, provider)
    return request.post<SocialLoginRO<UniUserInfoRO>>('oAuth/oAuthUserInfoAndPhoneNum', queryVO)
  }
}
