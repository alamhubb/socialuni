import socialHttp from '../plugins/http/socialHttp'
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
    return socialHttp.post('user/getMineUser')
  }

  static queryUserDetailAPI (userId: string) {
    return socialHttp.post('user/queryUserDetail', new UserQueryVO(userId))
  }

  static editUserAPI (user: UserEditVO) {
    return socialHttp.post('user/editUser', user)
  }

  static addUserImgAPI (userImg: ImgAddQO) {
    return socialHttp.post<CenterUserDetailRO>('user/addUserImg', userImg)
  }

  static deleteUserImgAPI (userImg: ImgFileVO) {
    return socialHttp.post('user/deleteUserImg', userImg)
  }

  static identityAuthAPI (userImg: DomFile) {
    return socialHttp.post('identity/auth', userImg)
  }

  static updateAvatarAPI (avatar: string) {
    return socialHttp.post('user/updateAvatar?avatar=' + avatar)
  }

  static getUserContactAPI (userId: string) {
    const user = new UserQueryVO(userId)
    return socialHttp.post<string>('user/getUserContact', user)
  }

  static switchUserContactAPI (openContact: boolean) {
    return socialHttp.post<string>('user/switchUserContact?openContact=' + openContact)
  }

  static userPayAPI (provider: string, payType: string, amount?: number) {
    const userPayVO = new UserPayVO(provider, payType, amount)
    return socialHttp.post<UserPayResultVO>('user/pay', userPayVO)
  }

  static destroyAccountAPI () {
    return socialHttp.post('user/destroyAccount')
  }
}
