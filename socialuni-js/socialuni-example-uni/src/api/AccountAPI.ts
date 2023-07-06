import request from "@socialuni/socialuni-api/src/request/request";
import UniProviderLoginQO from "@socialuni/socialuni-api/src/model/UniProviderLoginQO";


export default class AccountAPI {
    static bindSocialuniAccountAPI(loginData: UniProviderLoginQO) {
        return request.post<void>('account/bindSocialuniAccount', loginData)
    }
}
