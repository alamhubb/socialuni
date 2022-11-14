import ObjectUtil from "socialuni-use/src/utils/ObjectUtil";
import request from "socialuni-common/src/request/request";
import FrontErrorLogVO from "socialuni-user/src/model/FrontErrorLogVO";

export default class SocialuniAppAPI {
  static sendErrorLogAPI(uri: string, detail: string, params?: any, errorMsg?: any) {
    //如果已经是发送错误日志，不再重复发送
    if (uri && uri.indexOf('sendErrorLog') > -1) {
      return
    }
    return request.post('socialuni/app/sendErrorLog', new FrontErrorLogVO(uri, detail, ObjectUtil.toJson(params), ObjectUtil.toJson(errorMsg)))
  }
}
