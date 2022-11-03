import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";

export default class FollowStatus {
    // 私信
    static readonly follow: string = '关注'
    static readonly followed: string = '已关注'
    static readonly eachFollow: string = '互相关注'

    static getFollowStatus(user: CenterUserDetailRO) {
        if (user.hasFollowed && user.hasBeFollowed) {
            return FollowStatus.eachFollow
        } else if (user.hasFollowed) {
            return FollowStatus.followed
        } else {
            return FollowStatus.follow
        }
    }
}
