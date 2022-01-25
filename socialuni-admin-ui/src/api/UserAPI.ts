import request from '@/plugins/request'
import DevAccountRO from '@/model/base/DevAccountRO'
import ToastUtil from '@/utils/ToastUtil'

export default class UserAPI {
  static getUserAPI() {
    return request.post('user/getUser')
  }

  static resetSecretKeyAPI() {
    return request.post<string>('user/resetSecretKey')
  }

  static updateDevAccountAPI(user: DevAccountRO) {
    return request.post<DevAccountRO>('user/updateDevAccount', user).then(res => {
      ToastUtil.success('修改成功')
      return res
    })
  }

  static removeUserBanByPhoneNumAPI(phoneNum: number) {
    return request.post('user/removeUserBanByPhoneNum', phoneNum).then(res => {
      ToastUtil.success('解封成功')
      return res
    })
  }
}
