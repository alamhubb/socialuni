import SocialUserContentRO from "socialuni/src/model/social/SocialUserContentRO";

export default class FollowStatus {
    // 私信
    static readonly follow: string = '关注'
    static readonly followed: string = '已关注'
    static readonly backFollow: string = '回关'
    static readonly eachFollow: string = '互相关注'

    static getFollowStatus(user: SocialUserContentRO) {
        if (user.hasFollowed && user.hasBeFollowed) {
            return FollowStatus.eachFollow
        } else if (user.hasFollowed) {
            return FollowStatus.followed
        } else if (user.hasBeFollowed) {
            return FollowStatus.backFollow
        } else {
            return FollowStatus.follow
        }
    }
}
