import SocialUserSchoolNameEditQO from "../../model/QO/user/SocialUserSchoolNameEditQO";
import CenterUserDetailRO from "../../model/social/CenterUserDetailRO";
import request from '../../request/request';


export default class SocialuniUserExpandAPI {
  static editUserSchoolNameAPI (schoolName: string) {
    const qo = new SocialUserSchoolNameEditQO(schoolName)
    return request.post<CenterUserDetailRO>('socialuni/userExpand/editUserSchool', qo)
  }

  static editUserContactInfoAPI (contactInfo: string, openContactInfo: boolean) {
    return request.post<CenterUserDetailRO>('socialuni/userExpand/editUserContactInfo', {
      contactInfo,
      openContactInfo
    })
  }
}
