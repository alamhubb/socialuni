import LoginDataVO from '@/model/login/LoginDataVO'
import UniUtil from '@/utils/UniUtil'
import CommonUtil from '@/utils/CommonUtil'
import { systemModule } from '@/plugins/store'
import ProviderType, { Provider } from '@/const/ProviderType'
import JsonUtils from '@/utils/JsonUtil'
import PlatformType from '@/const/PlatformType'
import Constants from '@/const/Constant'
import BalaBala from '@/utils/BalaBala'
import Alert from '@/utils/Alert'
import ProviderUserVO from '@/plugins/uni/model/login/ProviderUserVO'
import UniUser from '@/plugins/uni/login/UniUser'
import LoginAPI from '@/plugins/social/api/LoginAPI'
import UserVO from '@/model/user/UserVO'
import UserStore from '@/plugins/store/UserStore'


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
        UserStore.loginAfter(res)
        const user = res.data.user
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
