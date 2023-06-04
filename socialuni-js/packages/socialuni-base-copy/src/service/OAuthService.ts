import LoginAPI from '../api/socialuni/LoginAPI'
import UserService from 'socialuni-sdk/src/service/UserService'
import UniProviderLoginQO from '@/socialuni/model/UniProviderLoginQO'
import LoginProvider from '@/socialuni/constant/LoginProvider'
import PhoneAPI from '@/socialuni/api/socialuni/PhoneAPI'
import { socialuniUserModule } from '@/socialuni/store'
import SocialLoginRO from '@/socialuni/model/social/SocialLoginRO'
import UniUserInfoRO from '@/socialuni/model/UniUserInfoRO'
import ResultRO from '@/socialuni/model/social/ResultRO'
import ToastUtil from '@/socialuni/utils/ToastUtil'
import PageUtil from '@/socialuni/utils/PageUtil'

export default class OAuthService {
  static async oAuthUserInfo (params: any) {
    if (params && params.referrerInfo) {
      const info = params.referrerInfo
      //这里可以返回回来的appId
      // appModule.threeProviderAppId = info.appId
      const extraData: ResultRO<SocialLoginRO<UniUserInfoRO>> = info.extraData
      if (extraData) {
        if (extraData.success) {
          const authData: SocialLoginRO<UniUserInfoRO> = extraData.data
          if (socialuniUserModule.hasUser) {
            await OAuthService.oAuthBindSocialuniPhone(authData)
          } else {
            await OAuthService.oAuthUserPhoneNumLogin(authData)
          }
          ToastUtil.toastLong('授权成功')
          PageUtil.toMinePage()
        }
      }
    }
  }

  static async oAuthUserPhoneNumLogin (oAuthRO: SocialLoginRO<UniUserInfoRO>) {
    const providerLoginQO: UniProviderLoginQO = new UniProviderLoginQO(oAuthRO.token, null, oAuthRO.user, LoginProvider.socialuni)

    const { data } = await LoginAPI.socialuniPhoneLoginAPI(providerLoginQO)

    UserService.getMineUserInitDataActionByToken(data)
  }

  //绑定清池手机号，需要已绑定清池手机号
  static async oAuthBindSocialuniPhone (oAuthRO: SocialLoginRO<UniUserInfoRO>) {
    const providerLoginQO: UniProviderLoginQO = new UniProviderLoginQO(oAuthRO.token, null, oAuthRO.user, LoginProvider.socialuni)

    const { data } = await PhoneAPI.bindSocialuniPhoneNum(providerLoginQO)

    socialuniUserModule.setUser(data)
  }
}
