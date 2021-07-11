import socialHttp from '@/plugins/social/http'
import FollowAddVO from '@/model/FollowAddVO'

export default class FollowAPI {
  static addFollowAPI (followAdd: FollowAddVO) {
    return socialHttp.post('follow/addFollow', followAdd)
  }

  static cancelFollowAPI (followAdd: FollowAddVO) {
    return socialHttp.post('follow/cancelFollow', followAdd)
  }

  static queryUserFollowsAPI () {
    return socialHttp.post('follow/queryUserFollows')
  }
}
