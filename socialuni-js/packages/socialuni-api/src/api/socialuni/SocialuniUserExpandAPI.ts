import SocialUserSchoolNameEditQO from "../../model/QO/user/SocialUserSchoolNameEditQO";
import CenterUserDetailRO from "../../model/social/CenterUserDetailRO";
import request from '../../../socialuni-common-api/src/request/request';
import SocialuniMineUserRO from "../../model/user/SocialuniMineUserRO";
import SocialuniUserExtendFriendQueryQO from "../../model/user/SocialuniUserExtendFriendQueryQO";


export default class SocialuniUserExpandAPI {
  static editUserSchoolNameAPI (schoolName: string) {
    const qo = new SocialUserSchoolNameEditQO(schoolName)
    return request.post<SocialuniMineUserRO>('socialuni/userExpand/editUserSchool', qo)
  }

  static editUserContactInfoAPI (contactInfo: string, openContactInfo: boolean) {
    return request.post<SocialuniMineUserRO>('socialuni/userExpand/editUserContactInfo', {
      contactInfo,
      openContactInfo
    })
  }

  static queryExtendFriendUsersAPI(queryQO: SocialuniUserExtendFriendQueryQO) {
    return request.post<CenterUserDetailRO[]>('socialuni/userExpand/queryExtendFriendUsers', queryQO)
  }

  static getUserContactInfoAPI(userId: string) {
    return request.get<string>('socialuni/userExpand/getUserContactInfo/' + userId)
  }
}
