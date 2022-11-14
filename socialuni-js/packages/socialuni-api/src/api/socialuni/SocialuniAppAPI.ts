import request from "socialuni-common/src/request/request";
import HomeSwiperVO from "../../model/HomeSwiperVO";
import TalkTabVO from "../../model/talk/TalkTabVO";

export default class SocialuniAppAPI {
  static getImgPathAPI() {
    return request.post<string>('socialuni/app/getImgPath')
  }

  static queryHomeSwipersAPI() {
    return request.post<HomeSwiperVO[]>('socialuni/communityApp/queryHomeSwipers')
  }

  static queryHomeTabsAPI() {
    return request.post<TalkTabVO[]>('socialuni/communityApp/queryHomeTabs')
  }

}
