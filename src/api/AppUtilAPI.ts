import http from '@/plugins/http'
import AppInitQueryVO from '@/model/common/AppInitQueryVO'
import AppUpdateVO from '@/model/app/AppUpdateVO'
import AppUpdateResultVO from '@/model/app/AppUpdateResultVO'
import FrontErrorLogVO from '@/model/app/FrontErrorLogVO'
import JsonUtil from '@/utils/JsonUtil'
import TalkQueryVO from '@/model/talk/TalkQueryVO'

export default class AppUtilAPI {
  static queryAppInitDataLoadAPI (initQueryVO: AppInitQueryVO) {
    return http.post('app/queryAppInitDataLoad', initQueryVO)
  }

  static queryAppInitDataReadyAPI () {
    return http.post('app/queryAppInitDataReady')
  }

  static checkUpdateAPI (versionCode: number) {
    const appUpdate = new AppUpdateVO(versionCode)
    return http.post<AppUpdateResultVO>('app/checkUpdate', appUpdate)
  }

  static sendErrorLogAPI (uri: string, detail: string, params?: any, errorMsg?: any) {
    //如果已经是发送错误日志，不再重复发送
    if (uri && uri.indexOf('sendErrorLog') > -1) {
      return
    }
    return http.post('app/sendErrorLog', new FrontErrorLogVO(uri, detail, JsonUtil.toJson(params), JsonUtil.toJson(errorMsg)))
  }
}
