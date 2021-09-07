import socialHttp from '/socialuni/plugins/http/socialHttp'
import AppUpdateVO from '/socialuni/model/app/AppUpdateVO'
import AppUpdateResultVO from '/socialuni/model/app/AppUpdateResultVO'
import FrontErrorLogVO from '/socialuni/model/app/FrontErrorLogVO'
import JsonUtil from '/socialuni/utils/JsonUtil'
import ThreeAuthRequestVO from '/socialuni/model/openData/ThreeAuthRequestVO'
import ThreeAuthUserInfoResultVO from '/socialuni/model/openData/ThreeAuthUserInfoResultVO'
import ThreeAuthUserPhoneNumResultVO from '/socialuni/model/openData/ThreeAuthUserPhoneNumResultVO'

export default class OpenDataAPI {
  static authUserInfoAPI () {
    const requestVO: ThreeAuthRequestVO = new ThreeAuthRequestVO()
    return socialHttp.post<ThreeAuthUserInfoResultVO>('user/authUserInfo', requestVO)
  }

  static authUserPhoneNumAPI () {
    const requestVO: ThreeAuthRequestVO = new ThreeAuthRequestVO()
    return socialHttp.post<ThreeAuthUserPhoneNumResultVO>('user/authUserPhoneNum', requestVO)
  }

  static queryAppInitDataReadyAPI () {
    return socialHttp.post('app/queryAppInitDataReady')
  }

  static checkUpdateAPI (versionCode: number) {
    const appUpdate = new AppUpdateVO(versionCode)
    return socialHttp.post<AppUpdateResultVO>('app/checkUpdate', appUpdate)
  }

  static sendErrorLog (uri: string, detail: string, params: any, errorMsg: any) {
    return socialHttp.post('app/sendErrorLog', new FrontErrorLogVO(uri, detail, JsonUtil.toJson(params), JsonUtil.toJson(errorMsg)))
  }
}
