import http from '@/plugins/http'
import AppInitDataRO from '@/model/common/AppInitDataRO'

export default class AppConfigAPI {
  static getAppConfigAPI () {
    return http.post<AppInitDataRO>('app/getAppLaunchData')
  }

  static getImgPathAPI () {
    return http.post<string>('app/getImgPath')
  }
}
