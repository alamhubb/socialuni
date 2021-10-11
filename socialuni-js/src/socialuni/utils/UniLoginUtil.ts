import LoginRes = UniApp.LoginRes
import UniPlatformType from '../const/UniPlatformType'
import { socialSystemModule } from '../store'

export default class UniLoginUtil {
  public static async getLoginCode (provider?: string): Promise<string> {
    const loginRes = await UniLoginUtil.login(provider)
    console.log(socialSystemModule.platform)
    if (UniPlatformType.mp === socialSystemModule.platform) {
      //小程序平台获取code
      return loginRes.code
    } else {
      //app平台使用access_token 作为code
      return (loginRes.authResult as any).access_token
    }
  }

  public static async login (provider?: any) {
    return new Promise<LoginRes>((resolve, reject) => {
      uni.login({
        provider: provider,
        success (loginRes) {
          resolve(loginRes)
        },
        fail (e) {
          reject(e)
        }
      })
    })
  }
}
