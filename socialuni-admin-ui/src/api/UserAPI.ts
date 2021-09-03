import request from '@/plugins/request'
import UserVO from '@/model/base/UserVO'
import ToastUtil from '@/utils/ToastUtil'

export default class UserAPI {
  static getUserAPI() {
    return request.post('user/getUser')
  }

  static resetSecretKeyAPI() {
    return request.post('user/resetSecretKey')
  }

  static updateDevAccountAPI(user: UserVO) {
    return request.post('user/updateUser', user).then(res => {
      ToastUtil.success('修改成功')
      return res
    })
  }
}
