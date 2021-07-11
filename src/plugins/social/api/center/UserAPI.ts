import socialHttp from '@/plugins/social/http'
import UserQueryVO from '@/model/user/UserQueryVO'
import UserPhoneNumVO from '@/model/user/UserPhoneNumVO'
import ImgFileVO from '@/model/ImgFileVO'
import UserPayVO from '@/model/user/UserPayVO'
import UserPayResultVO from '@/model/user/UserPayResultVO'
import UserEditVO from '@/model/user/UserEditVO'
import UserVO from '@/model/user/UserVO'
import UniProviderLoginQO from '@/plugins/uniapp-sdk/model/UniProviderLoginQO'

export default class UserAPI {
  static getMineUserInfoAPI () {
    return socialHttp.post('user/mine')
  }

  static bindProviderAccountAPI (data: UniProviderLoginQO) {
    return socialHttp.post('user/bindProviderAccount', data)
  }

  static getMineUserAPI () {
    return socialHttp.post('user/getMineUser')
  }

  static queryUserDetailAPI (userId: string) {
    return socialHttp.post('user/queryUserDetail', new UserQueryVO(userId))
  }

  static editUserAPI (user: UserEditVO) {
    return socialHttp.post('user/edit', user)
  }

  static sendAuthCodeAPI (phoneNum: string) {
    return socialHttp.post('phone/sendAuthCode?phoneNum=' + phoneNum)
  }

  static bindPhoneNumAPI (phoneNum: string, authCode: string) {
    const phoneNumObj: UserPhoneNumVO = new UserPhoneNumVO(phoneNum, authCode, null)
    return socialHttp.post<UserVO>('phone/bindPhoneNum', phoneNumObj).then(res => {
      return res.data
    })
  }

  static addUserImgAPI (userImg: ImgFileVO) {
    return socialHttp.post('user/addImg', userImg)
  }

  static deleteUserImgAPI (userImg: ImgFileVO) {
    return socialHttp.post('user/deleteImg', userImg)
  }

  static identityAuthAPI (userImg: ImgFileVO) {
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
