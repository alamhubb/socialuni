import request from '../../request/request';
import SocialuniUserRO from "../../model/user/SocialuniUserRO";
import ImgFileVO from "../../model/ImgFileVO";
import SocialUserContentRO from "../../model/social/SocialUserContentRO";
import UserQueryVO from "../../model/user/UserQueryVO";

export default class SocialuniUserAPI {
  static queryUserDetailAPI(userId: string) {
    return request.get<SocialuniUserRO>('socialuni/user/queryUserDetail/' + userId)
  }

  static getUserImgListAPI(userId: string) {
    return request.get<ImgFileVO[]>('socialuni/user/getUserImgList/' + userId)
  }
  static queryRecentlyUsersAPI() {
    return request.get<SocialUserContentRO[]>('socialuni/user/queryRecentlyUsers')
  }

  static getUserContactInfoAPI(userId: string) {
    return request.get<string>('socialuni/user/getUserContactInfo/'+userId)
  }
}
