import socialHttp from '@/socialuni/http/socialHttp'
import SocialLoginRO from '@/model/social/SocialLoginRO'
import CenterUserDetailRO from '@/model/social/CenterUserDetailRO'

export default class OAuthAPI {
  static mockApplySocialOAuthAPI () {
    return socialHttp.get<SocialLoginRO<CenterUserDetailRO>>('oAuth/mockApplySocialOAuth')
  }
}
