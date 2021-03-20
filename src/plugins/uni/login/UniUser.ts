import UniUtil from '@/utils/UniUtil'
import CommonUtil from '@/utils/CommonUtil'
import ProviderType, { Provider } from '@/plugins/uni/const/ProviderType'
import JsonUtil from '@/utils/JsonUtil'
import PlatformType from '@/const/PlatformType'
import { systemModule } from '@/plugins/store'
import Alert from '@/utils/Alert'
import LoginRes = UniApp.LoginRes;
import GetUserInfoRes = UniApp.GetUserInfoRes;
import ProviderUserVO from '@/plugins/uni/model/login/ProviderUserVO'

export default class UniUser {
  /**
   * 只有点击登录按钮时才会调用
   *
   * 以后需要考虑切换手机，登录同个账户的问题，也没准不用考虑，相同逻辑
   */
  //这一层就封装了，loading，获取手机号时不能提示登陆中
  static async getUserInfo (provider: Provider) {
    try {
      const user: ProviderUserVO = await UniUser.getLoginInfo(provider)
      const userInfoRes: GetUserInfoRes = await UniUtil.getUserInfo(provider)
      Object.assign(user, userInfoRes.userInfo)
      //如果小程序
      if (PlatformType.mp === user.platform) {
        //合并登陆信息
        Object.assign(user, userInfoRes.userInfo)
        //如果是微信小程序，需要后台解密
        if (systemModule.isMpWX) {
          user.iv = userInfoRes.iv
          user.encryptedData = userInfoRes.encryptedData
        }
      } else {
        const userInfo: any = userInfoRes.userInfo
        //如果没有用户信息报错
        if (provider === ProviderType.qq) {
          user.gender = userInfo.gender_type
        } else {
          return Promise.reject(Error('错误的登陆方式'))
        }
        //如果qq可以获取用户年龄
        user.birthday = userInfo.year
        //获取用户城市
        user.city = userInfo.city || userInfo.province
      }
      console.log(user)
      return user
    } catch (err) {
      Alert.error(err.message || err)
    }
  }

  static async getLoginInfo (provider: Provider) {
    const loginRes: LoginRes = await UniUtil.login(provider)
    const loginData: ProviderUserVO = new ProviderUserVO()
    loginData.provider = provider
    loginData.platform = systemModule.platform
    if (PlatformType.mp === loginData.platform) {
      //小程序平台获取code
      loginData.code = loginRes.code
    } else {
      //数据本身为字符串，需要解析，微信和qq都这样
      loginData.accessToken = JsonUtil.deepClone(loginRes.authResult).access_token
    }
    return loginData
  }
}
