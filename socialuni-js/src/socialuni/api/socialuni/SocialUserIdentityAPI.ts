import request from '../../plugins/http/request'
import SocialUserIdentityAuthQO from '@/socialuni/model/QO/user/SocialUserIdentityAuthQO'
import SocialUserIdentityAuthPreCheckRO from '@/socialuni/model/RO/user/SocialUserIdentityAuthPreCheckRO'

export default class SocialUserIdentityAPI {
  static userIdentityAuthPreCheckAPI(authQO: SocialUserIdentityAuthQO) {
    return request.post<SocialUserIdentityAuthPreCheckRO>('socialuni/userIdentity/userIdentityAuthPreCheck', authQO)
  }

  static userIdentityAuthAPI(authQO: SocialUserIdentityAuthQO) {
    return request.post<string>('socialuni/socialuni/userIdentity/userIdentityAuth', authQO)
  }
}
