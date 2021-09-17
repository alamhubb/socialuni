import LoginAPI from '../api/LoginAPI'
import UserService from './UserService'
import MockAPI from '../api/MockAPI'
import UniProviderLoginQO from '@/socialuni/model/UniProviderLoginQO'
import LoginProvider from '@/socialuni/const/LoginProvider'
import PhoneAPI from '@/socialuni/api/PhoneAPI'
import { socialUserModule } from '@/socialuni/store'
import AccountAPI from '@/socialuni/api/AccountAPI'

export default class MockService {
  static async mockSocialuniLogin () {
    const mockRes = await MockAPI.mockOAuthUserInfoAPI()
    const providerLoginQO: UniProviderLoginQO = new UniProviderLoginQO(mockRes.data.token, null, mockRes.data.user, LoginProvider.socialuni)

    const { data } = await LoginAPI.providerLoginAPI(providerLoginQO)

    UserService.getMineUserInitDataActionByToken(data)
  }

  static async mockSocialuniLoginAndAuthPhoneNum () {
    const mockRes = await MockAPI.mockOAuthUserPhoneNumAPI()
    const providerLoginQO: UniProviderLoginQO = new UniProviderLoginQO(mockRes.data.token, null, mockRes.data.user, LoginProvider.socialuni)

    const { data } = await LoginAPI.providerLoginAPI(providerLoginQO)

    UserService.getMineUserInitDataActionByToken(data)
  }

  //绑定清池手机号，需要已绑定清池手机号
  static async mockBindSocialuniPhone () {
    const mockRes = await MockAPI.mockOAuthUserPhoneNumAPI()
    const mineUser = socialUserModule.user
    if (!mineUser.bindedSocialuni) {
      const providerLoginQO: UniProviderLoginQO = new UniProviderLoginQO(mockRes.data.token, null, mockRes.data.user, LoginProvider.socialuni)
      await AccountAPI.bindSocialuniAccountAPI(providerLoginQO)
    }
    const { data } = await PhoneAPI.bindSocialuniPhoneNum()
    socialUserModule.setUser(data)
  }
}
