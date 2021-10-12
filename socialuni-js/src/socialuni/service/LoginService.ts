import LoginAPI from '../api/LoginAPI'
import UserService from './UserService'
import UniUserUtil from '../utils/UniUserUtil'
import SocialPhoneNumLoginQO from '../model/phone/SocialPhoneNumLoginQO'
import MockAPI from '../api/MockAPI'
import { socialSystemModule } from '../store'
import Constants from '../const/Constant'
import Toast from '../utils/Toast'
import UniProviderLoginQO from '@/socialuni/model/UniProviderLoginQO'
import LoginProvider from '@/socialuni/const/LoginProvider'

export default class LoginService {
  /**
   * 渠道登录的同一方法
   */
  static async providerLogin (provider: string, result: any) {
    if (socialSystemModule.isMp && socialSystemModule.isMpQQ && provider === LoginProvider.wx) {
      if (socialSystemModule.isMpQQ) {
        if (result.detail.errMsg !== Constants.loginSuccess) {
          return Toast.toast('您取消了登录')
        }
      }
    }
    //一行代码就可以获取登录所需要的信息, 还可以配合后台使用，一键登录，记住用户
    const loginQO = await UniUserUtil.getUniProviderLoginQO(provider)

    const { data } = await LoginAPI.providerLoginAPI(loginQO)

    UserService.getMineUserInitDataActionByToken(data)
  }

  static async phoneLogin (phoneNum: string, authCode: string) {
    const phoneBindQO = new SocialPhoneNumLoginQO(phoneNum, authCode)

    const { data } = await LoginAPI.phoneLoginAPI(phoneBindQO)

    UserService.getMineUserInitDataActionByToken(data)
  }

  /*static async mockSocialuniLogin () {
    const mockRes = await MockAPI.mockOAuthUserInfoAPI()
    const providerLoginQO: UniProviderLoginQO = new UniProviderLoginQO(mockRes.data.token, null, mockRes.data.user, LoginProvider.socialuni)

    const { data } = await LoginAPI.providerLoginAPI(providerLoginQO)

    UserService.getMineUserInitDataActionByToken(data)
  }*/
}
