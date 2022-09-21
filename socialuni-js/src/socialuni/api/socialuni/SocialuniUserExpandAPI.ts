import request from '../../plugins/http/request'
import SocialUserSchoolNameEditQO from '@/socialuni/model/QO/user/SocialUserSchoolNameEditQO'
import CenterUserDetailRO from '@/socialuni/model/social/CenterUserDetailRO'

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
