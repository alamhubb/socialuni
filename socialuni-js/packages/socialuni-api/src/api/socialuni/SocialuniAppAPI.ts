import request from "socialuni-api/src/request/request";
import HomeSwiperVO from "../../model/HomeSwiperVO";
import TalkTabVO from "../../model/talk/TalkTabVO";
import FrontErrorLogVO from "socialuni-api/src/model/FrontErrorLogVO";
import ObjectUtil from "socialuni-sdk/src/utils/ObjectUtil";

export default class SocialuniAppAPI {
  static getImgPathAPI() {
    return request.post<string>('socialuni/app/getImgPath')
  }

  static queryHomeSwipersAPI() {
    return request.post<HomeSwiperVO[]>('socialuni/app/queryHomeSwipers')
  }

  static queryHomeTabsAPI() {
    return request.post<TalkTabVO[]>('socialuni/app/queryHomeTabs')
  }

  static sendErrorLogAPI(uri: string, detail: string, params?: any, errorMsg?: any) {
    //如果已经是发送错误日志，不再重复发送
    if (uri && uri.indexOf('sendErrorLog') > -1) {
      return
    }
    return request.post('socialuni/app/sendErrorLog', new FrontErrorLogVO(uri, detail, ObjectUtil.toJson(params), ObjectUtil.toJson(errorMsg)))
  }
}
