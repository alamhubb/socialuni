import socialHttp from '@/plugins/social/http'
import ComLoginRO from '@/plugins/social/model/ComLoginRO'
import SocialUserRO from '@/plugins/social/model/SocialUserRO'

export default class OAuthAPI {
  static mockApplySocialOAuthAPI () {
    return socialHttp.get<ComLoginRO<SocialUserRO>>('oAuth/mockApplySocialOAuth')
  }
}
