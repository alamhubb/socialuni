import LoginRes = UniApp.LoginRes
import {socialSystemModule} from "socialuni-sdk/src/store/store";
import UniPlatformType from "socialuni-constant/constant/uni/UniPlatformType";

export default class UniLoginUtil {
  public static async getLoginCode (provider?: string): Promise<string> {
    const loginRes = await UniLoginUtil.login(provider)
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
