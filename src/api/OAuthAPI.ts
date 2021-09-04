import http from '@/plugins/http'
import SocialLoginRO from '@/model/social/SocialLoginRO'
import CenterUserDetailRO from '@/model/social/CenterUserDetailRO'

export default class OAuthAPI {
  static mockApplySocialOAuthAPI () {
    return http.get<SocialLoginRO<CenterUserDetailRO>>('oAuth/mockApplySocialOAuth')
  }
}
