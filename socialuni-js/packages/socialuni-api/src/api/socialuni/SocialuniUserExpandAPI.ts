import SocialUserSchoolNameEditQO from "../../model/QO/user/SocialUserSchoolNameEditQO";
import CenterUserDetailRO from "../../model/social/CenterUserDetailRO";
import request from '../../request/request';
import SocialuniMineUserRO from "../../model/user/SocialuniMineUserRO";


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
}
