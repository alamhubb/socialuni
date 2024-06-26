import socialuniUserRequest from "../request/socialuniUserRequest";
import SocialuniUserRO from "socialuni-api-base/src/model/user/SocialuniUserRO";
import SocialuniMineUserRO from "socialuni-api-base/src/model/user/SocialuniMineUserRO";
import ImgFileVO from "socialuni-api-base/src/model/ImgFileVO";
import UserEditVO from "socialuni-api-base/src/model/user/UserEditVO";
import ImgAddQO from "socialuni-api-base/src/model/user/ImgAddQO";
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import SocialLoginRO from "socialuni-api-base/src/model/social/SocialLoginRO";

export interface SocialuniGetRandomAvatarRO{
  path:string
  fullPath:string
}

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

  static randomUserAvatar() {
    return socialuniUserRequest.get<SocialuniMineUserRO>('socialuni/mineUser/randomUserAvatar')
  }

  static addUserAvatarImgAPI(userImg: ImgAddQO) {
    return socialuniUserRequest.post<SocialuniUserRO>('socialuni/mineUser/addUserAvatarImg', userImg)
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

  /**
   * 刷新token
   * @param loginData
   */
  static refreshToken() {
    return socialuniUserRequest.get<SocialLoginRO<SocialuniUserRO>>('socialuni/mineUser/refreshToken');
  }
}
