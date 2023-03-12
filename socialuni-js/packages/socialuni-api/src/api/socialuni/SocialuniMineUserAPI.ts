import ImgFileVO from '../../../../socialuni-api/src/model/ImgFileVO'
import request from '../../request/request';
import UserEditVO from "socialuni-api/src/model/user/UserEditVO";
import ImgAddQO from "socialuni-api/src/model/user/ImgAddQO";
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";
import UserQueryVO from "socialuni-api/src/model/user/UserQueryVO";
import UserPayResultVO from "socialuni-api/src/model/user/UserPayResultVO";
import SocialuniPayCoinQO from "socialuni-api/src/model/user/SocialuniPayCoinQO";
import SocialuniUserRO from "../../model/user/SocialuniUserRO";
import SocialuniMineUserRO from "../../model/user/SocialuniMineUserRO";

export default class SocialuniMineUserAPI {
  static getMineUserInfoAPI() {
    return request.get<SocialuniMineUserRO>('socialuni/mineUser/getMineUser')
  }

  static queryUserDetailAPI(userId: string) {
    return request.get<SocialuniUserRO>('socialuni/mineUser/queryUserDetail/' + userId)
  }

  static editUserAPI(user: UserEditVO) {
    return request.post<SocialuniMineUserRO>('socialuni/mineUser/editUser', user)
  }

  static addUserImgAPI(userImg: ImgAddQO) {
    return request.post<CenterUserDetailRO>('socialuni/mineUser/addUserImg', userImg)
  }

  static addUserAvatarImgAPI(userImg: ImgAddQO) {
    return request.post<CenterUserDetailRO>('socialuni/mineUser/addUserAvatarImg', userImg)
  }

  static deleteUserImgAPI(userImg: ImgFileVO) {
    return request.post('socialuni/mineUser/deleteImg', userImg)
  }

  static deleteUserImgNewAPI(userImg: ImgFileVO) {
    return request.post('socialuni/mineUser/deleteUserImg', {userImgId: userImg.id})
  }

  static updateAvatarAPI(avatar: string) {
    return request.post('socialuni/mineUser/updateAvatar?avatar=' + avatar)
  }

  static switchUserContactAPI(openContact: boolean) {
    return request.post<string>('socialuni/mineUser/switchUserContact?openContact=' + openContact)
  }

  static destroyAccountAPI() {
    return request.post('socialuni/mineUser/destroyAccount')
  }
}
