import MockAPI from '../api/MockAPI'
import OAuthService from '@/socialuni/service/OAuthService'

export default class MockService {
  static async mockOAuthUserPhoneNumLogin () {
    //统一使用授权手机号登录
    const mockRes = await MockAPI.mockOAuthUserPhoneNumAPI()
    await OAuthService.oAuthUserPhoneNumLogin(mockRes.data)
  }

  //绑定清池手机号，需要已绑定清池手机号
  static async mockBindSocialuniPhone () {
    const mockRes = await MockAPI.mockOAuthUserPhoneNumAPI()
    await OAuthService.oAuthBindSocialuniPhone(mockRes.data)
  }
}
