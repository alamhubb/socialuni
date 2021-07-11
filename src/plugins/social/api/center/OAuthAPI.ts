import socialHttp from '@/plugins/social/http'
import SocialLoginRO from '@/plugins/social/model/SocialLoginRO'

//清池专用api
export default class OAuthAPI {
  static mockApplySocialOAuthAPI () {
    return socialHttp.get<SocialLoginRO>('oAuth/mockApplySocialOAuth')
  }
}
