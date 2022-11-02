import AppInitDataRO from "../../model/common/AppInitDataRO";
import request from "../../request/request";
import HomeSwiperVO from "../../model/HomeSwiperVO";
import TalkTabVO from "../../model/talk/TalkTabVO";
import AppUpdateResultVO from "../../model/app/AppUpdateResultVO";
import AppUpdateVO from "../../model/app/AppUpdateVO";
import FrontErrorLogVO from "../../model/app/FrontErrorLogVO";
import ObjectUtil from "socialuni-sdk/src/utils/ObjectUtil";

export default class SocialuniAppAPI {
  static getAppConfigAPI() {
    return request.post<AppInitDataRO>('socialuni/app/getAppLaunchData')
  }

  static getImgPathAPI() {
    return request.post<string>('socialuni/app/getImgPath')
  }

  static queryHomeSwipersAPI() {
    return request.post<HomeSwiperVO[]>('socialuni/app/queryHomeSwipers')
  }

  static queryHomeTabsAPI() {
    return request.post<TalkTabVO[]>('socialuni/app/queryHomeTabs')
  }

  static checkUpdateAPI(versionCode: number) {
    const appUpdate = new AppUpdateVO(versionCode)
    return request.post<AppUpdateResultVO>('socialuni/app/checkUpdate', appUpdate)
  }

  static sendErrorLogAPI(uri: string, detail: string, params?: any, errorMsg?: any) {
    //如果已经是发送错误日志，不再重复发送
    if (uri && uri.indexOf('sendErrorLog') > -1) {
      return
    }
    return request.post('socialuni/app/sendErrorLog', new FrontErrorLogVO(uri, detail, ObjectUtil.toJson(params), ObjectUtil.toJson(errorMsg)))
  }
}
