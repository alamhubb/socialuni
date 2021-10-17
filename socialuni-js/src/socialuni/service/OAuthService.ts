import LoginAPI from '../api/LoginAPI'
import UserService from './UserService'
import UniProviderLoginQO from '@/socialuni/model/UniProviderLoginQO'
import LoginProvider from '@/socialuni/const/LoginProvider'
import PhoneAPI from '@/socialuni/api/PhoneAPI'
import { socialUserModule } from '@/socialuni/store'
import SocialLoginRO from '@/socialuni/model/social/SocialLoginRO'
import UniUserInfoRO from '@/socialuni/model/UniUserInfoRO'

export default class OAuthService {
  static async oAuthUserPhoneNumLogin (oAuthRO: SocialLoginRO<UniUserInfoRO>) {
    const providerLoginQO: UniProviderLoginQO = new UniProviderLoginQO(oAuthRO.token, null, oAuthRO.user, LoginProvider.socialuni)

    const { data } = await LoginAPI.socialuniPhoneLoginAPI(providerLoginQO)

    UserService.getMineUserInitDataActionByToken(data)
  }

  //绑定清池手机号，需要已绑定清池手机号
  static async oAuthBindSocialuniPhone (oAuthRO: SocialLoginRO<UniUserInfoRO>) {
    const providerLoginQO: UniProviderLoginQO = new UniProviderLoginQO(oAuthRO.token, null, oAuthRO.user, LoginProvider.socialuni)

    const { data } = await PhoneAPI.bindSocialuniPhoneNum(providerLoginQO)

    socialUserModule.setUser(data)
  }
}
