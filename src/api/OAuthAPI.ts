import http from '@/plugins/http'
import SocialLoginRO from '@/model/social/SocialLoginRO'
import SocialUserRO from '@/model/social/SocialUserRO'

export default class OAuthAPI {
  static mockApplySocialOAuthAPI () {
    return http.get<SocialLoginRO<SocialUserRO>>('oAuth/mockApplySocialOAuth')
  }
}
