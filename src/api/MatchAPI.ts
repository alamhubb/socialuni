import http from '@/plugins/http'
import MatchQueryVO from '@/model/match/MatchQueryVO'

export default class MatchAPI {
  static queryMatchUsersAPI (userIds: string[], matchType: string) {
    return http.post('match/queryMatchUsers', new MatchQueryVO(userIds, matchType))
  }

  static likeUserAPI (userId: string) {
    return http.post('match/likeMatchUser?userId=' + userId)
  }

  static unlikeUserAPI (userId: string) {
    return http.post('match/unlikeMatchUser?userId=' + userId)
  }
}
