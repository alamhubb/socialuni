import request from "socialuni/src/request/request";
import TalkTabVO from "socialuni/src/model/talk/TalkTabVO";
import AppUpdateResultVO from "../../../../socialuni/src/model/app/AppUpdateResultVO";
import AppUpdateVO from "../../../../socialuni/src/model/app/AppUpdateVO";
import HomeSwiperVO from "../HomeSwiperVO";
import AppInitDataRO from "socialuni/src/model/AppInitDataRO";

export default class SocialuniCommunityAPI {
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
}
