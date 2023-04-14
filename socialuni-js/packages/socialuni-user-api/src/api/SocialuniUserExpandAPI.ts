import socialuniUserRequest from "../request/socialuniUserRequest";
import SocialuniMineUserRO from "socialuni-base-api/src/model/user/SocialuniMineUserRO";
import SocialUserSchoolNameEditQO from "socialuni-base-api/src/model/QO/user/SocialUserSchoolNameEditQO";
import SocialuniUserExtendFriendQueryQO from "socialuni-base-api/src/model/user/SocialuniUserExtendFriendQueryQO";
import CenterUserDetailRO from "socialuni-base-api/src/model/social/CenterUserDetailRO";

export default class SocialuniUserExpandAPI {
  static editUserSchoolNameAPI (schoolName: string) {
    const qo = new SocialUserSchoolNameEditQO(schoolName)
    return socialuniUserRequest.post<SocialuniMineUserRO>('socialuni/userExpand/editUserSchool', qo)
  }

  static editUserContactInfoAPI (contactInfo: string, openContactInfo: boolean) {
    return socialuniUserRequest.post<SocialuniMineUserRO>('socialuni/userExpand/editUserContactInfo', {
      contactInfo,
      openContactInfo
    })
  }

  static queryExtendFriendUsersAPI(queryQO: SocialuniUserExtendFriendQueryQO) {
    return socialuniUserRequest.post<CenterUserDetailRO[]>('socialuni/userExpand/queryExtendFriendUsers', queryQO)
  }

  static getUserContactInfoAPI(userId: string) {
    return socialuniUserRequest.get<string>('socialuni/userExpand/getUserContactInfo/' + userId)
  }
}
