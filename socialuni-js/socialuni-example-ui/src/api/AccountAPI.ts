import request from "socialuni-common/src/request/request";
import UniProviderLoginQO from "socialuni-api/src/model/UniProviderLoginQO";


export default class AccountAPI {
    static bindSocialuniAccountAPI(loginData: UniProviderLoginQO) {
        return request.post<void>('account/bindSocialuniAccount', loginData)
    }
}
