import socialuniCommunityRequest from "../request/socialuniCommunityRequest";
import FollowAddVO from "socialuni-base-api/src/model/FollowAddVO";
import SocialuniPageQueryQO from "socialuni-base-api/src/model/common/SocialuniPageQueryQO";
import SocialUserFollowDetailRO from "socialuni-base-api/src/model/social/SocialUserFollowDetailRO";

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
