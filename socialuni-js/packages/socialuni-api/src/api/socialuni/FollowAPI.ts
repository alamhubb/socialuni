import FollowAddVO from '../../model/FollowAddVO'
import request from "socialuni-api/src/request/request";

export default class FollowAPI {
  static addFollowAPI(followAdd: FollowAddVO) {
    return request.post('socialuni/follow/addFollow', followAdd)
  }

  static cancelFollowAPI(followAdd: FollowAddVO) {
    return request.post('socialuni/follow/cancelFollow', followAdd)
  }

  static queryUserFollowsAPI() {
    return request.get('socialuni/follow/queryUserFollows')
  }
}
