import SocialuniUserExtendFriendQueryQO from "socialuni-api-base/src/model/user/SocialuniUserExtendFriendQueryQO";
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import socialuniUserRequest from "socialuni-user-api/src/request/socialuniUserRequest";
import SocialuniUserIdQO from "socialuni-api-base/src/model/QO/user/SocialuniUserIdQO";
import SocialuniUserExtendDetailRO from "../model/SocialuniUserExtendDetailRO";

export default class SocialuniUserLikeAPI {

  static addUserLikeAPI(user: SocialuniUserExtendDetailRO) {
    return socialuniUserRequest.post<CenterUserDetailRO[]>('socialuni/userLike/likeUser', {userId: user.id})
  }
}
