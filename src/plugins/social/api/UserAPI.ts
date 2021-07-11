import socialHttp from '@/plugins/social/http'
import SocialUserRO from '@/plugins/social/model/SocialUserRO'
import UniProviderLoginQO from '@/plugins/uniapp-sdk/model/UniProviderLoginQO'

export default class UserAPI {
  static getMineUserAPI () {
    return socialHttp.post<SocialUserRO>('user/getMineUser')
  }

  static bindProviderAccountAPI (data: UniProviderLoginQO) {
    return socialHttp.post('user/bindProviderAccount', data)
  }
}
