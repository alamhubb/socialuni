import request from '../plugins/http/request'
import SocialUserIdentityAuthQO from '@/socialuni/model/QO/user/SocialUserIdentityAuthQO'
import SocialUserIdentityAuthPreCheckRO from '@/socialuni/model/RO/user/SocialUserIdentityAuthPreCheckRO'

export default class SocialUserIdentityAPI {
  static userIdentityAuthPreCheckAPI (authQO: SocialUserIdentityAuthQO) {
    return request.post<SocialUserIdentityAuthPreCheckRO>('userIdentity/userIdentityAuthPreCheck', authQO)
  }

  static identityAuthAPI (authQO: SocialUserIdentityAuthQO) {
    return request.post('user/identityAuth')
  }
}
