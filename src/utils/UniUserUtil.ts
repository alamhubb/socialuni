import UniProviderLoginQO from '../model/UniProviderLoginQO'
import UniLoginUtil from './UniLoginUtil'
import UniUserInfoRO from '../model/UniUserInfoRO'
import UniProviderType from '../const/UniProviderType'
import GetUserInfoRes = UniApp.GetUserInfoRes
import { uniSystemModule } from '@/store'

export default class UniUserUtil {
  public static getUniProviderLoginQO (provider?: any): Promise<UniProviderLoginQO> {
    return Promise.all([UniLoginUtil.getLoginCode(provider), UniUserUtil.getUserInfo(provider)]).then(res => {
      const userInfo: UniProviderLoginQO = new UniProviderLoginQO()
      userInfo.platform = uniSystemModule.platform
      //如果为小程序的话不传值，默认为小程序类型
      userInfo.provider = provider || uniSystemModule.mpPlatform

      userInfo.code = res[0] || ''

      const userInfoRes: GetUserInfoRes = res[1]
      userInfo.iv = userInfoRes.iv || ''
      userInfo.encryptedData = userInfoRes.encryptedData || ''

      const userInfoRO: UniUserInfoRO = userInfoRes.userInfo as UniUserInfoRO
      userInfo.openId = userInfoRO.openId || ''
      userInfo.unionId = userInfoRO.unionId || ''

      userInfo.nickName = userInfoRO.nickName || ''
      userInfo.avatarUrl = userInfoRO.avatarUrl || ''
      userInfo.gender = userInfoRO.gender || 0
      userInfo.country = userInfoRO.country || ''
      userInfo.province = userInfoRO.province || ''
      userInfo.city = userInfoRO.city || ''

      if (uniSystemModule.isApp) {
        if (provider === UniProviderType.qq) {
          userInfo.gender = userInfoRO.gender_type || 0
        }
        //如果qq可以获取用户年龄
        userInfo.birthday = userInfoRO.year || ''
      }
      return userInfo
    })
  }

  public static getUserInfo (provider?: any) {
    return new Promise<GetUserInfoRes>((resolve, reject) => {
      if (uniSystemModule.isWX) {
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
