import socialHttp from '/socialuni/plugins/http/socialHttp'
import MatchQueryVO from '/socialuni/model/match/MatchQueryVO'

export default class MatchAPI {
  static queryMatchUsersAPI (userIds: string[], matchType: string) {
    return socialHttp.post('match/queryMatchUsers', new MatchQueryVO(userIds, matchType))
  }

  static likeUserAPI (userId: string) {
    return socialHttp.post('match/likeMatchUser?userId=' + userId)
  }

  static unlikeUserAPI (userId: string) {
    return socialHttp.post('match/unlikeMatchUser?userId=' + userId)
  }
}
