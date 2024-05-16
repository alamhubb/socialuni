import socialuniUserRequest from "../request/socialuniUserRequest";
import SocialuniUserRO from "socialuni-api-base/src/model/user/SocialuniUserRO";
import SocialuniMineUserRO from "socialuni-api-base/src/model/user/SocialuniMineUserRO";
import ImgFileVO from "socialuni-api-base/src/model/ImgFileVO";
import UserEditVO from "socialuni-api-base/src/model/user/UserEditVO";
import ImgAddQO from "socialuni-api-base/src/model/user/ImgAddQO";
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";

export interface SocialuniGetRandomAvatarRO{
  path:string
  fullPath:string
}

export default class SocialuniUserImgAPI {

  static addUserImgAPI(userImg: ImgAddQO) {
    return socialuniUserRequest.post<SocialuniMineUserRO>('socialuni/userImg/addUserImg', userImg)
  }

  static deleteUserImgAPI(userImg: ImgFileVO) {
    return socialuniUserRequest.post('socialuni/mineUser/deleteImg', userImg)
  }

  static deleteUserImgNewAPI(userImg: ImgFileVO) {
    return socialuniUserRequest.post('socialuni/mineUser/deleteUserImg', {userImgId: userImg.id})
  }

  static getUserImgListAPI(userId: string) {
    return socialuniUserRequest.get<ImgFileVO[]>('socialuni/userImg/getUserImgList/' + userId)
  }

  static getMineUserImgListAPI(userId: string) {
    return socialuniUserRequest.get<ImgFileVO[]>('socialuni/userImg/getMineUserImgList')
  }
}
