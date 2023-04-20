import socialuniCommunityRequest from "../request/socialuniCommunityRequest";
import FollowAddVO from "socialuni/src/model/FollowAddVO";
import SocialuniPageQueryQO from "socialuni/src/model/common/SocialuniPageQueryQO";
import SocialUserFollowDetailRO from "socialuni/src/model/social/SocialUserFollowDetailRO";

export default class FollowAPI {
    static addFollowAPI(followAdd: FollowAddVO) {
        return socialuniCommunityRequest.post('socialuni/follow/addFollow', followAdd)
    }

    static cancelFollowAPI(followAdd: FollowAddVO) {
        return socialuniCommunityRequest.post('socialuni/follow/cancelFollow', followAdd)
    }

    static queryUserFollowsAPI(queryQO: SocialuniPageQueryQO<SocialUserFollowDetailRO, string>) {
        return socialuniCommunityRequest.post<SocialUserFollowDetailRO[]>('socialuni/follow/queryUserFollows', queryQO)
    }
}
