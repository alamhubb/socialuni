import socialHttp from '../plugins/http/socialHttp'
import AppInitQueryVO from '../model/common/AppInitQueryVO'
import AppUpdateVO from '../model/app/AppUpdateVO'
import AppUpdateResultVO from '../model/app/AppUpdateResultVO'
import FrontErrorLogVO from '../model/app/FrontErrorLogVO'
import JsonUtil from '../utils/JsonUtil'

export default class AppUtilAPI {
  static queryAppInitDataLoadAPI (initQueryVO: AppInitQueryVO) {
    return socialHttp.post('app/queryAppInitDataLoad', initQueryVO)
  }

  static queryAppInitDataReadyAPI () {
    return socialHttp.post('app/queryAppInitDataReady')
  }

  static checkUpdateAPI (versionCode: number) {
    const appUpdate = new AppUpdateVO(versionCode)
    return socialHttp.post<AppUpdateResultVO>('app/checkUpdate', appUpdate)
  }

  static sendErrorLogAPI (uri: string, detail: string, params?: any, errorMsg?: any) {
    //如果已经是发送错误日志，不再重复发送
    if (uri && uri.indexOf('sendErrorLog') > -1) {
      return
    }
    return socialHttp.post('app/sendErrorLog', new FrontErrorLogVO(uri, detail, JsonUtil.toJson(params), JsonUtil.toJson(errorMsg)))
  }
}
