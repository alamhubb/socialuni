import UniProviderLoginQO from '../model/UniProviderLoginQO'
import UniLoginUtil from './UniLoginUtil'
import UniUserInfoRO from '../model/UniUserInfoRO'
import UniProviderType from '../const/UniProviderType'
import { socialSystemModule } from '../store'
import GetUserInfoRes = UniApp.GetUserInfoRes

export default class UniUserUtil {
  public static async getUniProviderLoginQO (provider: string): Promise<UniProviderLoginQO> {
    console.log(3333)
    //顺序不能改变，必须先获取用户信息，再获取code，要不然会报错因为获取code会重置事件
    const userInfo: GetUserInfoRes = await UniUserUtil.getUserInfo(provider)
    const providerCode = await UniLoginUtil.getLoginCode(provider)
    const providerLoginQO: UniProviderLoginQO = new UniProviderLoginQO(providerCode, userInfo, provider)
    return providerLoginQO
  }

  public static getUserInfo (provider?: any) {
    return new Promise<GetUserInfoRes>((resolve, reject) => {
      if (socialSystemModule.isMpWx) {
        //只有为小程序，且为微信小程序时才
        uni.getUserProfile({
          provider: provider,
          desc: 'getUserInfo',
          success (userInfo) {
            resolve(userInfo)
          },
          fail (e) {
            reject(e)
          }
        })
      } else {
        uni.getUserInfo({
          provider: provider,
          success (userInfo) {
            resolve(userInfo)
          },
          fail (e) {
            reject(e)
          }
        })
      }
    })
  }
}
