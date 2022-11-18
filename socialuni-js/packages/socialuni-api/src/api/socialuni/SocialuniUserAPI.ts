import request from "socialuni-api/src/request/request";
import SocialuniUserRO from "../../model/user/SocialuniUserRO";
import ImgFileVO from "../../model/ImgFileVO";
import SocialUserContentRO from "../../model/social/SocialUserContentRO";

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
}
