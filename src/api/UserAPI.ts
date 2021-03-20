import http from '@/plugins/http'
import UserQueryVO from '@/model/user/UserQueryVO'
import UserPhoneNumVO from '@/model/user/UserPhoneNumVO'
import ImgFileVO from '@/model/ImgFileVO'
import UserPayVO from '@/model/user/UserPayVO'
import UserPayResultVO from '@/model/user/UserPayResultVO'
import UserEditVO from "@/model/user/UserEditVO";

export default class UserAPI {
  static getMineUserInfoAPI() {
    return http.post('user/mine')
  }

  static queryUserDetailAPI(userId: number) {
    return http.post('user/queryUserDetail', new UserQueryVO(userId))
  }

  static editUserAPI(user: UserEditVO) {
    return http.post('user/edit', user)
  }

  static sendAuthCodeAPI(phoneNum: string) {
    return http.post('phone/sendAuthCode?phoneNum=' + phoneNum)
  }

  static bindPhoneNumAPI(phoneNum: string, authCode: string) {
    const phoneNumObj: UserPhoneNumVO = new UserPhoneNumVO(phoneNum, authCode, null)
    return http.post('phone/bindPhoneNum', phoneNumObj)
  }

  static addUserImgAPI(userImg: ImgFileVO) {
    return http.post('user/addImg', userImg)
  }

  static deleteUserImgAPI(userImg: ImgFileVO) {
    return http.post('user/deleteImg', userImg)
  }

  static identityAuthAPI(userImg: ImgFileVO) {
    return http.post('identity/auth', userImg)
  }

  static updateAvatarAPI(avatar: string) {
    return http.post('user/updateAvatar?avatar=' + avatar)
  }

  static getUserContactAPI(userId: number) {
    const user = new UserQueryVO(userId)
    return http.post<string>('user/getUserContact', user)
  }

  static switchUserContactAPI(openContact: boolean) {
    return http.post<string>('user/switchUserContact?openContact=' + openContact)
  }

  static userPayAPI(provider: string, payType: string, amount?: number) {
    const userPayVO = new UserPayVO(provider, payType, amount)
    return http.post<UserPayResultVO>('user/pay', userPayVO)
  }

  static destroyAccountAPI() {
    return http.post('user/destroyAccount')
  }
}
