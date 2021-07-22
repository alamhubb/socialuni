import UniUtil from '@/utils/UniUtil'
import { userModule } from '@/store'
import ErrorCode from '@/const/ErrorCode'
import Toast from '@/utils/Toast'
import PhoneAPI from '@/api/PhoneAPI'
import BindWxPhoneNumQO from '@/model/phone/BindWxPhoneNumQO'

export default class PhoneService {
  static async bindPhoneNum (phoneNum: string, authCode: string) {
    //手机号绑定
    const user = await PhoneAPI.bindPhoneNumAPI(phoneNum, authCode)
    userModule.setUser(user)
  }

  static async bindWxPhoneNum (wxGetPhoneInfoResult: any) {
    if (wxGetPhoneInfoResult.detail.errMsg === 'getPhoneNumber:ok') {
      // 默认未过期
      try {
        await UniUtil.checkSession()
      } catch {
        await PhoneService.getWxPhoneNumberByLogin(wxGetPhoneInfoResult)
      }
      const loginData: BindWxPhoneNumQO = new BindWxPhoneNumQO()
      Object.assign(loginData, wxGetPhoneInfoResult.detail)
      loginData.sessionEnable = true
      //前台获取为未过期，但也有可能已过期，尝试调用后台获取，确认是否未过期
      try {
        return await PhoneService.bindPhoneNumAction(loginData)
      } catch (error) {
        //如果为自定义，则将过期标示改为已过期调用后台
        if (error.errorCode === ErrorCode.custom) {
          Toast.toast(error.data)
          PhoneService.getWxPhoneNumberByLogin(wxGetPhoneInfoResult)
          throw error
        }
      }
    } else {
      Toast.toast('您选择了不绑定')
      throw Error('您选择了不绑定')
    }
  }

  //微信绑定手机号使用
  static getWxPhoneNumberByLogin (wxGetPhoneInfoResult: any) {
    /*LoginService.getLoginData(systemModule.mpPlatform as Provider).then((loginData: ProviderUserVO) => {
      Object.assign(loginData, wxGetPhoneInfoResult.detail)
      // 代表已过期
      loginData.sessionEnable = false
    })*/
  }

  //跳转清池绑定手机号
  static async bindPhoneNumAction (loginData: BindWxPhoneNumQO) {
    return PhoneAPI.bindWxPhoneNumAPI(loginData).then((res) => {
      userModule.setUser(res.data)
      Toast.toast('绑定手机号成功')
      return res.data
    })
  }
}
