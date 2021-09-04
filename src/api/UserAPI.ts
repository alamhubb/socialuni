import http from '@/plugins/http'
import UserQueryVO from '@/model/user/UserQueryVO'
import ImgFileVO from '@/model/ImgFileVO'
import UserPayVO from '@/model/user/UserPayVO'
import UserPayResultVO from '@/model/user/UserPayResultVO'
import UserEditVO from '@/model/user/UserEditVO'
import ImgAddQO from '@/model/user/ImgAddQO'
import DomFile from '@/model/DomFile'
import CenterUserDetailRO from '@/model/social/CenterUserDetailRO'

export default class UserAPI {
  static getMineUserInfoAPI () {
    return http.post('user/getMineUser')
  }

  static queryUserDetailAPI (userId: string) {
    return http.post('user/queryUserDetail', new UserQueryVO(userId))
  }

  static editUserAPI (user: UserEditVO) {
    return http.post('user/editUser', user)
  }

  static addUserImgAPI (userImg: ImgAddQO) {
    return http.post<CenterUserDetailRO>('user/addUserImg', userImg)
  }

  static deleteUserImgAPI (userImg: ImgFileVO) {
    return http.post('user/deleteUserImg', userImg)
  }

  static identityAuthAPI (userImg: DomFile) {
    return http.post('identity/auth', userImg)
  }

  static updateAvatarAPI (avatar: string) {
    return http.post('user/updateAvatar?avatar=' + avatar)
  }

  static getUserContactAPI (userId: string) {
    const user = new UserQueryVO(userId)
    return http.post<string>('user/getUserContact', user)
  }

  static switchUserContactAPI (openContact: boolean) {
    return http.post<string>('user/switchUserContact?openContact=' + openContact)
  }

  static userPayAPI (provider: string, payType: string, amount?: number) {
    const userPayVO = new UserPayVO(provider, payType, amount)
    return http.post<UserPayResultVO>('user/pay', userPayVO)
  }

  static destroyAccountAPI () {
    return http.post('user/destroyAccount')
  }
}
