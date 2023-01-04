import ImgFileVO from '../../../../socialuni-api/src/model/ImgFileVO'
import request from '../../request/request';
import UserEditVO from "socialuni-api/src/model/user/UserEditVO";
import ImgAddQO from "socialuni-api/src/model/user/ImgAddQO";
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";
import UserQueryVO from "socialuni-api/src/model/user/UserQueryVO";
import UserPayResultVO from "socialuni-api/src/model/user/UserPayResultVO";
import UserPayVO from "socialuni-api/src/model/user/UserPayVO";
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

  static getUserContactAPI(userId: string) {
    const user = new UserQueryVO(userId)
    return request.post<string>('socialuni/mineUser/getUserContact', user)
  }

  static switchUserContactAPI(openContact: boolean) {
    return request.post<string>('socialuni/mineUser/switchUserContact?openContact=' + openContact)
  }

  static userPayAPI(provider: string, payType: string, amount?: number) {
    const userPayVO = new UserPayVO(provider, payType, amount)
    return request.post<UserPayResultVO>('socialuni/mineUser/pay', userPayVO)
  }

  static destroyAccountAPI() {
    return request.post('socialuni/mineUser/destroyAccount')
  }
}
