import request from "socialuni-api/src/request/request";
import SocialuniUserRO from "../../model/user/SocialuniUserRO";
import ImgFileVO from "../../model/ImgFileVO";

export default class SocialuniUserAPI {
  static queryUserDetailAPI(userId: string) {
    return request.get<SocialuniUserRO>('socialuni/user/queryUserDetail/' + userId)
  }

  static getUserImgListAPI(userId: string) {
    return request.get<ImgFileVO[]>('socialuni/user/getUserImgList/' + userId)
  }
}
