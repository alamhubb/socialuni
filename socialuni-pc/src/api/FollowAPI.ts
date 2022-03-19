import request from '../plugins/http/request'
import FollowAddVO from '../model/FollowAddVO'

export default class FollowAPI {
  static addFollowAPI (followAdd: FollowAddVO) {
    return request.post('follow/addFollow', followAdd)
  }

  static cancelFollowAPI (followAdd: FollowAddVO) {
    return request.post('follow/cancelFollow', followAdd)
  }

  static queryUserFollowsAPI () {
    return request.post('follow/queryUserFollows')
  }
}
