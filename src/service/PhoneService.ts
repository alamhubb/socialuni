import UniUtil from '@/utils/UniUtil'
import ProviderUserVO from '@/model/ProviderUserVO'
import { userModule } from '@/store'
import ErrorCode from '@/const/ErrorCode'
import Toast from '@/utils/Toast'

export default class PhoneService {
  static async getPhoneNumberByWx (obj: any) {
    if (obj.detail.errMsg === 'getPhoneNumber:ok') {
      // 默认未过期
      await UniUtil.checkSession().catch(() => {
        UserService.getWxPhoneNumberByLogin(obj)
      })
      const loginData: ProviderUserVO = new ProviderUserVO()
      Object.assign(loginData, obj.detail)
      loginData.sessionEnable = true
      //前台获取为未过期，但也有可能已过期，尝试调用后台获取，确认是否未过期
      try {
        return await userModule.bindPhoneNumAction(loginData)
      } catch (error) {
        //如果为自定义，则将过期标示改为已过期调用后台
        if (error.errorCode === ErrorCode.custom) {
          Toast.toast(error.data)
          UserService.getWxPhoneNumberByLogin(obj)
          throw error
        }
      }
    } else {
      Toast.toast('您选择了不绑定')
      throw Error('您选择了不绑定')
    }
  }
}
