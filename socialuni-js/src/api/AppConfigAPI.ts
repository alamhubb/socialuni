import request from '../socialuni/plugins/http/request'
import AppInitDataRO from '../socialuni/model/common/AppInitDataRO'
import HomeSwiperVO from "@/socialuni/model/HomeSwiperVO";
import TalkTabVO from "@/socialuni/model/talk/TalkTabVO";

export default class AppConfigAPI {
  static getAppConfigAPI() {
    return request.post<AppInitDataRO>('app/getAppLaunchData')
  }

  static getImgPathAPI() {
    return request.post<string>('app/getImgPath')
  }

  static queryHomeSwipersAPI() {
    return request.post<HomeSwiperVO[]>('app/queryHomeSwipers')
  }

  static queryHomeTabsAPI() {
    return request.post<TalkTabVO[]>('app/queryHomeTabs')
  }
}
