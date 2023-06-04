import socialuniUserRequest from "../request/socialuniUserRequest";
import SocialuniUserRO from "socialuni-api-base/src/model/user/SocialuniUserRO";
import SocialuniMineUserRO from "socialuni-api-base/src/model/user/SocialuniMineUserRO";
import ImgFileVO from "socialuni-api-base/src/model/ImgFileVO";
import UserEditVO from "socialuni-api-base/src/model/user/UserEditVO";
import ImgAddQO from "socialuni-api-base/src/model/user/ImgAddQO";
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";

export default class SocialuniMineUserAPI {
  static getMineUserInfoAPI() {
    return socialuniUserRequest.get<SocialuniMineUserRO>('socialuni/mineUser/getMineUser')
  }

  static queryUserDetailAPI(userId: string) {
    return socialuniUserRequest.get<SocialuniUserRO>('socialuni/mineUser/queryUserDetail/' + userId)
  }

  static editUserAPI(user: UserEditVO) {
    return socialuniUserRequest.post<SocialuniMineUserRO>('socialuni/mineUser/editUser', user)
  }

  static addUserImgAPI(userImg: ImgAddQO) {
    return socialuniUserRequest.post<CenterUserDetailRO>('socialuni/mineUser/addUserImg', userImg)
  }

  static addUserAvatarImgAPI(userImg: ImgAddQO) {
    return socialuniUserRequest.post<CenterUserDetailRO>('socialuni/mineUser/addUserAvatarImg', userImg)
  }

  static deleteUserImgAPI(userImg: ImgFileVO) {
    return socialuniUserRequest.post('socialuni/mineUser/deleteImg', userImg)
  }

  static deleteUserImgNewAPI(userImg: ImgFileVO) {
    return socialuniUserRequest.post('socialuni/mineUser/deleteUserImg', {userImgId: userImg.id})
  }

  static updateAvatarAPI(avatar: string) {
    return socialuniUserRequest.post('socialuni/mineUser/updateAvatar?avatar=' + avatar)
  }

  static switchUserContactAPI(openContact: boolean) {
    return socialuniUserRequest.post<string>('socialuni/mineUser/switchUserContact?openContact=' + openContact)
  }

  static destroyAccountAPI() {
    return socialuniUserRequest.post('socialuni/mineUser/destroyAccount')
  }
}
