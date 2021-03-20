import GetSettingSuccessResult = UniApp.GetSettingSuccessResult;
import AuthSetting = UniApp.AuthSetting;

// 统一处理各平台的订阅
export default class AppAuthUtil {
  //获取用户是否授权了用户信息

  //获取用户是否授权了位置信息
  static getUserAuthLocation(): Promise<boolean> {
    return new Promise((resolve, reject) => {
      uni.getSetting({
        success: ((res: GetSettingSuccessResult) => {
          const authSetting: AuthSetting = res.authSetting
          const authLocation = authSetting && authSetting["scope.userLocation"]
          if (authLocation) {
            resolve(authLocation)
          } else {
            reject(authLocation)
          }
        }),
        fail: (err => {
          reject(err)
        })
      })
    })
  }
}
