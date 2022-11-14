import OAuthUserInfoQO from "socialuni-api/src/model/dev/OAuthUserInfoQO";
import DevAccountRO from "socialuni-api/src/model/dev/DevAccountRO";
import request from "socialuni-common/src/request/request";

export default class DevAccountAPI {
    static queryDevAccountAPI(appId: string, provider: string) {
        const queryVO: OAuthUserInfoQO = new OAuthUserInfoQO(appId, provider)
        return request.post<DevAccountRO>('dev/queryDevAccount', queryVO)
  }
}
