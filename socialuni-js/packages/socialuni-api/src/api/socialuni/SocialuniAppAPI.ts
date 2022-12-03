import request from "socialuni-api/src/request/request";
import HomeSwiperVO from "../../model/HomeSwiperVO";
import SocialuniTalkTabRO from "../../model/talk/SocialuniTalkTabRO";
import FrontErrorLogVO from "socialuni-api/src/model/FrontErrorLogVO";
import ObjectUtil from "socialuni-sdk/src/utils/ObjectUtil";
import AppUpdateResultVO from "../../model/app/AppUpdateResultVO";
import AppInitDataRO from "../../model/config/AppInitDataRO";
import AppUpdateVO from "../../model/app/AppUpdateVO";

export default class SocialuniAppAPI {
  static getImgPathAPI() {
    return request.post<string>('socialuni/app/getImgPath')
  }

  static getAppConfigAPI() {
    return request.post<AppInitDataRO>('socialuni/app/getAppLaunchData')
  }

  static checkUpdateAPI(versionCode: number) {
    const appUpdate = new AppUpdateVO(versionCode)
    return request.post<AppUpdateResultVO>('socialuni/app/checkUpdate', appUpdate)
  }

  static queryHomeSwipersAPI() {
    return request.post<HomeSwiperVO[]>('socialuni/app/queryHomeSwipers')
  }

  static queryHomeTabsAPI() {
    return request.post<SocialuniTalkTabRO[]>('socialuni/app/queryHomeTabs')
  }

  static sendErrorLogAPI(uri: string, detail: string, params?: any, errorMsg?: any) {
    //如果已经是发送错误日志，不再重复发送
    if (uri && uri.indexOf('sendErrorLog') > -1) {
      return
    }
    return request.post('socialuni/app/sendErrorLog', new FrontErrorLogVO(uri, detail, ObjectUtil.toJson(params), ObjectUtil.toJson(errorMsg)))
  }
}
