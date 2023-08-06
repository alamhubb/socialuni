import SocialuniUserExtendFriendQueryQO from "@socialuni/socialuni-api-base/src/model/user/SocialuniUserExtendFriendQueryQO";
import CenterUserDetailRO from "@socialuni/socialuni-api-base/src/model/social/CenterUserDetailRO";
import socialuniUserRequest from "@socialuni/socialuni-user-api/src/request/socialuniUserRequest";

export default class SocialuniExpandAPI {

  static queryExtendFriendUsersAPI(queryQO: SocialuniUserExtendFriendQueryQO) {
    return socialuniUserRequest.post<CenterUserDetailRO[]>('socialuni/userExpand/queryExtendFriendUsers', queryQO)
  }

  static getUserContactInfoAPI(userId: string) {
    return socialuniUserRequest.get<string>('socialuni/userExpand/getUserContactInfo/' + userId)
  }
}
