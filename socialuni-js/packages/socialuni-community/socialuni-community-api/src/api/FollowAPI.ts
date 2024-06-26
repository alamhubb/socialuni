import socialuniCommunityRequest from "../request/socialuniCommunityRequest";
import FollowAddVO from "socialuni-api-base/src/model/FollowAddVO";
import SocialuniPageQueryQO from "socialuni-api-base/src/model/common/SocialuniPageQueryQO";
import SocialUserFollowDetailRO from "socialuni-api-base/src/model/social/SocialUserFollowDetailRO";
import SocialuniUserFansDetailRO from "socialuni-user-api/src/model/SocialuniUserFansDetailRO";

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

    static getMineUserFollowDetailAPI() {
        return socialuniCommunityRequest.get<SocialuniUserFansDetailRO>('socialuni/follow/getMineUserFollowDetail')
    }
}
