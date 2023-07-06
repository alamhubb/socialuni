import OAuthUserInfoQO from "@socialuni/socialuni-api/src/model/dev/OAuthUserInfoQO";
import DevAccountRO from "@socialuni/socialuni-api/src/model/dev/DevAccountRO";
import request from "@socialuni/socialuni-api/src/request/request";

export default class DevAccountAPI {
    static queryDevAccountAPI(appId: string, provider: string) {
        const queryVO: OAuthUserInfoQO = new OAuthUserInfoQO(appId, provider)
        return request.post<DevAccountRO>('dev/queryDevAccount', queryVO)
  }
}
