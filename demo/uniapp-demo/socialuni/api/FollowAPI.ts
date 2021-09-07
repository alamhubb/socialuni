import socialHttp from '/socialuni/plugins/http/socialHttp'
import FollowAddVO from '/socialuni/model/FollowAddVO'

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
