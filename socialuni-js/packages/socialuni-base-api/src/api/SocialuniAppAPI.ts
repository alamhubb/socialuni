import FrontErrorLogVO from "socialuni-base-api/src/model/FrontErrorLogVO";
import ObjectUtil from "socialuni-util/src/util/ObjectUtil";
import socialuniAppRequest from "../request/socialuniAppRequest";
import AppInitDataRO from "../model/config/AppInitDataRO";
import AppUpdateResultVO from "../model/app/AppUpdateResultVO";
import HomeSwiperVO from "../model/HomeSwiperVO";
import AppUpdateVO from "../model/app/AppUpdateVO";
import SocialuniTalkTabRO from "../model/talk/SocialuniTalkTabRO";
import JsonUtil from "../util/JsonUtil";


export default class SocialuniAppAPI {
  static getImgPathAPI() {
    return socialuniAppRequest.post<string>('socialuni/app/getImgPath')
  }

  static getAppConfigAPI() {
    return socialuniAppRequest.post<AppInitDataRO>('socialuni/app/getAppLaunchData')
  }

  static checkUpdateAPI(versionCode: number) {
    const appUpdate = new AppUpdateVO(versionCode)
    return socialuniAppRequest.post<AppUpdateResultVO>('socialuni/app/checkUpdate', appUpdate)
  }

  static queryHomeSwipersAPI() {
    return socialuniAppRequest.post<HomeSwiperVO[]>('socialuni/app/queryHomeSwipers')
  }

  static queryHomeTabsAPI() {
    return socialuniAppRequest.post<SocialuniTalkTabRO[]>('socialuni/app/queryHomeTabs')
  }

  static sendErrorLogAPI(uri: string, detail: string, params?: any, errorMsg?: any) {
    //如果已经是发送错误日志，不再重复发送
    if (uri && uri.indexOf('sendErrorLog') > -1) {
      return
    }
    return socialuniAppRequest.post('socialuni/app/sendErrorLog', new FrontErrorLogVO(uri, detail, JsonUtil.toJson(params), ObjectUtil.toJson(errorMsg)))
  }
}
