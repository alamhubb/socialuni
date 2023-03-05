import FollowAddVO from '../../model/FollowAddVO'
import request from '../../request/request';
import SocialuniPageQueryQO from "../../model/common/SocialuniPageQueryQO";
import SocialUserContentRO from "../../model/social/SocialUserContentRO";

export default class FollowAPI {
    static addFollowAPI(followAdd: FollowAddVO) {
        return request.post('socialuni/follow/addFollow', followAdd)
    }

    static cancelFollowAPI(followAdd: FollowAddVO) {
        return request.post('socialuni/follow/cancelFollow', followAdd)
    }

    static queryUserFollowsAPI(queryQO: SocialuniPageQueryQO<SocialUserContentRO>) {
        return request.post('socialuni/follow/queryUserFollows', queryQO)
    }
}
