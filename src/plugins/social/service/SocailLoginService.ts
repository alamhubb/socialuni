import UniUtil from '@/plugins/uni/UniUtil'
import { systemModule } from '@/plugins/store'
import ProviderType, { Provider } from '@/const/ProviderType'
import Alert from '@/utils/Alert'
import ProviderUserVO from '@/plugins/uni/model/login/ProviderUserVO'
import UniUser from '@/plugins/uni/login/UniUser'
import LoginAPI from '@/plugins/social/api/LoginAPI'
import UserStoreCom from '@/plugins/store/UserStoreCom'


export default class SocialLoginService {
  /**
   * 渠道登录的同一方法
   */
  static async providerLogin (provider: Provider, loginData?: ProviderUserVO) {
    UniUtil.showLoading('登录中')
    try {
      if (provider !== ProviderType.phone) {
        loginData = await UniUser.getUserInfo(provider as Provider)
      }
      if (systemModule.isApp) {
        loginData.clientid = systemModule.clientid
      }
      return LoginAPI.providerLoginAPI(loginData).then((res: any) => {
        const user = UserStoreCom.getMineUserInitDataActionByToken(res)
        let hintText = '登录成功'
        if (!user.phoneNum) {
          hintText += '，绑定手机号后才可发布内容'
        }
        if (systemModule.isIosAndMpQQ) {
          hintText += '，如遇无法弹出输入框，请重启应用'
        }
        Alert.hint(hintText)
        return user
      })
    } finally {
      UniUtil.hideLoading()
    }
  }
}
