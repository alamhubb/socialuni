import socialHttp from '/socialuni/plugins/http/socialHttp'
import SocialLoginRO from '/socialuni/model/social/SocialLoginRO'
import CenterUserDetailRO from '/socialuni/model/social/CenterUserDetailRO'

export default class OAuthAPI {
  static mockApplySocialOAuthAPI () {
    return socialHttp.get<SocialLoginRO<CenterUserDetailRO>>('oAuth/mockApplySocialOAuth')
  }
}
