import LoginRes = UniApp.LoginRes;
import UniPlatformType from '../const/UniPlatformType'
import { systemModule } from '@/store'

export default class UniLoginUtil {
  public static getLoginCode (provider?: any): Promise<string> {
    return UniLoginUtil.login(provider).then((loginRes: LoginRes) => {
      if (UniPlatformType.mp === systemModule.platform) {
        //小程序平台获取code
        return loginRes.code
      } else {
        //app平台使用access_token 作为code
        return (loginRes.authResult as any).access_token
      }
    })
  }

  public static login (provider?: any) {
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
