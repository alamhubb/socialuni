import UniUtil from '@/utils/UniUtil'
import { systemModule } from '@/store'
import Alert from '@/utils/Alert'
import LoginAPI from '@/api/LoginAPI'
import UserService from '@/service/UserService'
import UniUserUtil from '@/utils/UniUserUtil'
import PhoneFormData from '@/model/login/PhoneFormData'


export default class SocialLoginService {
  /**
   * 渠道登录的同一方法
   */
  static async providerLogin (provider: string) {
    UniUtil.showLoading('登录中')
    try {
      //一行代码就可以获取登录所需要的信息, 还可以配合后台使用，一键登录，记住用户
      const loginQO = await UniUserUtil.getUniProviderLoginQO(provider)

      const { data } = await LoginAPI.providerLoginAPI(loginQO)

      const user = UserService.getMineUserInitDataActionByToken(data)

      let hintText = '登录成功'
      if (!user.phoneNum) {
        hintText += '，绑定手机号后才可发布内容'
      }
      if (systemModule.isIosAndMpQQ) {
        hintText += '，如遇无法弹出输入框，请重启应用'
      }
      Alert.hint(hintText)
    } finally {
      UniUtil.hideLoading()
    }
  }

  static async phoneLogin (phoneNum: string, authCode: string) {
    UniUtil.showLoading('登录中')
    try {
      const phoneBindQO = new PhoneFormData(phoneNum, authCode)

      const { data } = await LoginAPI.phoneLoginAPI(phoneBindQO)

      const user = UserService.getMineUserInitDataActionByToken(data)

      let hintText = '登录成功'
      if (!user.phoneNum) {
        hintText += '，绑定手机号后才可发布内容'
      }
      if (systemModule.isIosAndMpQQ) {
        hintText += '，如遇无法弹出输入框，请重启应用'
      }
      Alert.hint(hintText)
    } finally {
      UniUtil.hideLoading()
    }
  }
}
