import request from '../plugins/http/request'
import AppInitDataRO from '../model/common/AppInitDataRO'

export default class AppConfigAPI {
  static getAppConfigAPI () {
    return request.post<AppInitDataRO>('app/getAppLaunchData')
  }

  static getImgPathAPI () {
    return request.post<string>('app/getImgPath')
  }
}
