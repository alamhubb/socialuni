import socialHttp from '../plugins/http/socialHttp'
import AppInitDataRO from '../model/common/AppInitDataRO'

export default class AppConfigAPI {
  static getAppConfigAPI () {
    return socialHttp.post<AppInitDataRO>('app/getAppLaunchData')
  }

  static getImgPathAPI () {
    return socialHttp.post<string>('app/getImgPath')
  }
}
