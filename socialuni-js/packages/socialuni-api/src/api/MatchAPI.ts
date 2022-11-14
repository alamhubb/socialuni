import request from 'socialuni-common/src/request'
import MatchQueryVO from '../model/match/MatchQueryVO'

export default class MatchAPI {
    static queryMatchUsersAPI(userIds: string[], matchType: string) {
        return request.post('match/queryMatchUsers', new MatchQueryVO(userIds, matchType))
    }

    static likeUserAPI(userId: string) {
        return request.post('match/likeMatchUser?userId=' + userId)
    }

    static unlikeUserAPI(userId: string) {
        return request.post('match/unlikeMatchUser?userId=' + userId)
    }
}
