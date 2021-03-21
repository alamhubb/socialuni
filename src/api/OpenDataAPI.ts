import http from '@/plugins/http'
import AppUpdateVO from '@/model/app/AppUpdateVO'
import AppUpdateResultVO from '@/model/app/AppUpdateResultVO'
import FrontErrorLogVO from '@/model/app/FrontErrorLogVO'
import JsonUtil from '@/utils/JsonUtil'
import ThreeAuthRequestVO from '@/model/openData/ThreeAuthRequestVO'
import ThreeAuthUserInfoResultVO from '@/model/openData/ThreeAuthUserInfoResultVO'
import ThreeAuthUserPhoneNumResultVO from '@/model/openData/ThreeAuthUserPhoneNumResultVO'

export default class OpenDataAPI {
  static authUserInfoAPI () {
    const requestVO: ThreeAuthRequestVO = new ThreeAuthRequestVO()
    return http.post<ThreeAuthUserInfoResultVO>('user/authUserInfo', requestVO)
  }

  static authUserPhoneNumAPI () {
    const requestVO: ThreeAuthRequestVO = new ThreeAuthRequestVO()
    return http.post<ThreeAuthUserPhoneNumResultVO>('user/authUserPhoneNum', requestVO)
  }

  static queryAppInitDataReadyAPI () {
    return http.post('app/queryAppInitDataReady')
  }

  static checkUpdateAPI (versionCode: number) {
    const appUpdate = new AppUpdateVO(versionCode)
    return http.post<AppUpdateResultVO>('app/checkUpdate', appUpdate)
  }

  static sendErrorLog (uri: string, detail: string, params: any, errorMsg: any) {
    return http.post('app/sendErrorLog', new FrontErrorLogVO(uri, detail, JsonUtil.toJson(params), JsonUtil.toJson(errorMsg)))
  }
}
