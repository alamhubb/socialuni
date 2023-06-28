import SocialuniMineUserRO from "socialuni-api-base/src/model/user/SocialuniMineUserRO";
import SocialUserSchoolNameEditQO from "socialuni-api-base/src/model/QO/user/SocialUserSchoolNameEditQO";
import socialuniUserRequest from "socialuni-user-api/src/request/socialuniUserRequest";

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
}