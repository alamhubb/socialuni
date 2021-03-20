import LoginDataVO from '@/model/login/LoginDataVO'
import UniUtil from '@/utils/UniUtil'
import CommonUtil from '@/utils/CommonUtil'
import { systemModule, userModule } from '@/plugins/store'
import ProviderType, { Provider } from '@/const/ProviderType'
import Alert from '@/utils/Alert'
import ProviderUserVO from '@/plugins/uni/model/login/ProviderUserVO'
import UniUser from '@/plugins/uni/login/UniUser'
import LoginAPI from '@/plugins/social/api/LoginAPI'
import UserStoreCom from '@/plugins/store/UserStoreCom'


export default class SocialLoginService {
  /**
   * 渠道登陆的同一方法
   */
  static async providerLogin (provider: Provider) {
    UniUtil.showLoading('登录中')
    try {
      const loginData: ProviderUserVO = await UniUser.getUserInfo(provider)
      if (systemModule.isApp) {
        loginData.clientid = systemModule.clientid
      }
      return LoginAPI.providerLoginAPI(loginData).then((res: any) => {
        const user = UserStoreCom.getMineUserInitDataActionByToken(res)
        let hintText = '登录成功'
        if (!user.phoneNum) {
          hintText += '，绑定手机号后才可发布内容'
        }
        Alert.hint(hintText)
        return user
      })
    } finally {
      UniUtil.hideLoading()
    }
  }
}
