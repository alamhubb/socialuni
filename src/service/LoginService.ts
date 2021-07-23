import UniUtil from '@/utils/UniUtil'
import LoginAPI from '@/api/LoginAPI'
import UserService from '@/service/UserService'
import UniUserUtil from '@/utils/UniUserUtil'
import PhoneNumQO from '@/model/phone/PhoneNumQO'


export default class LoginService {
  /**
   * 渠道登录的同一方法
   */
  static async providerLogin (provider: string) {
    UniUtil.showLoading('登录中')
    try {
      //一行代码就可以获取登录所需要的信息, 还可以配合后台使用，一键登录，记住用户
      const loginQO = await UniUserUtil.getUniProviderLoginQO(provider)

      const { data } = await LoginAPI.providerLoginAPI(loginQO)

      UserService.getMineUserInitDataActionByToken(data)
    } finally {
      UniUtil.hideLoading()
    }
  }

  static async phoneLogin (phoneNum: string, authCode: string) {
    UniUtil.showLoading('登录中')
    try {
      const phoneBindQO = new PhoneNumQO(phoneNum, authCode)

      const { data } = await LoginAPI.phoneLoginAPI(phoneBindQO)

      UserService.getMineUserInitDataActionByToken(data)
    } finally {
      UniUtil.hideLoading()
    }
  }
}
