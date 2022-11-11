import UserQueryVO from '../../model/user/UserQueryVO'
import ImgFileVO from '../../../../socialuni-user/src/model/ImgFileVO'
import UserPayVO from '../../model/user/UserPayVO'
import UserPayResultVO from '../../model/user/UserPayResultVO'
import UserEditVO from '../../model/user/UserEditVO'
import ImgAddQO from '../../model/user/ImgAddQO'
import CenterUserDetailRO from '../../model/social/CenterUserDetailRO'
import request from "socialuni-common/src/request/request";

export default class SocialuniUserAPI {
  static getMineUserInfoAPI() {
    return request.get('socialuni/user/getMineUser')
  }

  static queryUserDetailAPI(userId: string) {
    return request.get('socialuni/user/queryUserDetail/' + userId)
  }

  static editUserAPI(user: UserEditVO) {
    return request.post('socialuni/user/editUser', user)
  }

  static addUserImgAPI(userImg: ImgAddQO) {
    return request.post<CenterUserDetailRO>('socialuni/user/addUserImg', userImg)
  }

  static addUserAvatarImgAPI(userImg: ImgAddQO) {
    return request.post<CenterUserDetailRO>('socialuni/user/addUserAvatarImg', userImg)
  }

  static deleteUserImgAPI(userImg: ImgFileVO) {
    return request.post('socialuni/user/deleteImg', userImg)
  }


  static deleteUserImgNewAPI(userImg: ImgFileVO) {
    return request.post('socialuni/user/deleteUserImg', {userImgId: userImg.id})
  }

  static updateAvatarAPI(avatar: string) {
    return request.post('socialuni/user/updateAvatar?avatar=' + avatar)
  }

  static getUserContactAPI(userId: string) {
    const user = new UserQueryVO(userId)
    return request.post<string>('socialuni/user/getUserContact', user)
  }

  static switchUserContactAPI(openContact: boolean) {
    return request.post<string>('socialuni/user/switchUserContact?openContact=' + openContact)
  }

  static userPayAPI(provider: string, payType: string, amount?: number) {
    const userPayVO = new UserPayVO(provider, payType, amount)
    return request.post<UserPayResultVO>('socialuni/user/pay', userPayVO)
  }

  static destroyAccountAPI() {
    return request.post('socialuni/user/destroyAccount')
  }

  static getUserImgListAPI(userId: string) {
    return request.get<ImgFileVO[]>('socialuni/user/getUserImgList/' + userId)
  }
}
