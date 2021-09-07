import socialHttp from '/socialuni/plugins/http/socialHttp'
import AppInitDataRO from '/socialuni/model/common/AppInitDataRO'

export default class AppConfigAPI {
  static getAppConfigAPI () {
    return socialHttp.post<AppInitDataRO>('app/getAppLaunchData')
  }

  static getImgPathAPI () {
    return socialHttp.post<string>('app/getImgPath')
  }
}
