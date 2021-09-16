import LoginAPI from '../api/LoginAPI'
import UserService from './UserService'
import MockAPI from '../api/MockAPI'
import UniProviderLoginQO from '@/socialuni/model/UniProviderLoginQO'
import LoginProvider from '@/socialuni/const/LoginProvider'
import PhoneAPI from '@/socialuni/api/PhoneAPI'
import { socialUserModule } from '@/socialuni/store'

export default class MockService {
  static async mockSocialuniLogin () {
    const mockRes = await MockAPI.mockOAuthUserInfoAPI()
    const providerLoginQO: UniProviderLoginQO = new UniProviderLoginQO(mockRes.data.token, null, mockRes.data.user, LoginProvider.socialuni)

    const { data } = await LoginAPI.providerLoginAPI(providerLoginQO)

    UserService.getMineUserInitDataActionByToken(data)
  }

  static async mockBindSocialuniPhone () {
    await MockAPI.mockOAuthUserPhoneNumAPI()

    const { data } = await PhoneAPI.bindSocialuniPhoneNum()

    socialUserModule.setUser(data)
  }
}
