import SocialuniMineUserRO from "@socialuni/socialuni-api-base/src/model/user/SocialuniMineUserRO";
import SocialUserSchoolNameEditQO from "@socialuni/socialuni-api-base/src/model/QO/user/SocialUserSchoolNameEditQO";
import socialuniUserRequest from "../request/socialuniUserRequest";

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
