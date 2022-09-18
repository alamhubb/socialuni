import request from '../../plugins/http/request'
import AppInitDataRO from '../../model/common/AppInitDataRO'
import HomeSwiperVO from "@/socialuni/model/HomeSwiperVO";
import TalkTabVO from "@/socialuni/model/talk/TalkTabVO";
import AppUpdateVO from "@/socialuni/model/app/AppUpdateVO";
import AppUpdateResultVO from "@/socialuni/model/app/AppUpdateResultVO";
import FrontErrorLogVO from "@/socialuni/model/app/FrontErrorLogVO";
import ObjectUtil from "@/socialuni/utils/ObjectUtil";

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
