import UniUtil from '@/utils/UniUtil'
import { systemModule, userModule } from '@/store'
import ProviderType, { Provider } from '@/const/ProviderType'
import Alert from '@/utils/Alert'
import ProviderUserVO from '@/model/ProviderUserVO'
import UniUser from '@/utils/UniUser'
import LoginAPI from '@/api/LoginAPI'
import UserService from '@/service/UserService'
import UniUserUtil from '@/utils/UniUserUtil'
import UniProviderLoginQO from '@/model/UniProviderLoginQO'


export default class SocialLoginService {
  /**
   * 渠道登录的同一方法
   */
  static async providerLogin () {
    UniUtil.showLoading('登录中')
    try {
      //一行代码就可以获取登录所需要的信息, 还可以配合后台使用，一键登录，记住用户
      const loginQO = await UniUserUtil.getUniProviderLoginQO()

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
}
