import request from '../plugins/http/request'
import UserQueryVO from '../model/user/UserQueryVO'
import ImgFileVO from '../model/ImgFileVO'
import UserPayVO from '../model/user/UserPayVO'
import UserPayResultVO from '../model/user/UserPayResultVO'
import UserEditVO from '../model/user/UserEditVO'
import ImgAddQO from '../model/user/ImgAddQO'
import DomFile from '../model/DomFile'
import CenterUserDetailRO from '../model/social/CenterUserDetailRO'

export default class UserAPI {
  static getMineUserInfoAPI () {
    return request.post('user/getMineUser')
  }

  static queryUserDetailAPI (userId: string) {
    return request.post('user/queryUserDetail', new UserQueryVO(userId))
  }

  static editUserAPI (user: UserEditVO) {
    return request.post('user/editUser', user)
  }

  static addUserImgAPI (userImg: ImgAddQO) {
    return request.post<CenterUserDetailRO>('user/addUserImg', userImg)
  }

  static deleteUserImgAPI (userImg: ImgFileVO) {
    return request.post('user/deleteImg', userImg)
  }


  static deleteUserImgNewAPI (userImg: ImgFileVO) {
    return request.post('user/deleteUserImg', { userImgId: userImg.id })
  }


  static identityAuthAPI (userImg: DomFile) {
    return request.post('identity/auth', userImg)
  }

  static updateAvatarAPI (avatar: string) {
    return request.post('user/updateAvatar?avatar=' + avatar)
  }

  static getUserContactAPI (userId: string) {
    const user = new UserQueryVO(userId)
    return request.post<string>('user/getUserContact', user)
  }

  static switchUserContactAPI (openContact: boolean) {
    return request.post<string>('user/switchUserContact?openContact=' + openContact)
  }

  static userPayAPI (provider: string, payType: string, amount?: number) {
    const userPayVO = new UserPayVO(provider, payType, amount)
    return request.post<UserPayResultVO>('user/pay', userPayVO)
  }

  static destroyAccountAPI () {
    return request.post('user/destroyAccount')
  }
}
