import socialuniUserRequest from "../request/socialuniUserRequest";
import UniProviderLoginQO from "socialuni-api-base/src/model/login/UniProviderLoginQO";
import SocialLoginRO from "socialuni-api-base/src/model/social/SocialLoginRO";
import SocialuniMineUserRO from "socialuni-api-base/src/model/user/SocialuniMineUserRO";
import SocialPhoneNumLoginQO from "socialuni-api-base/src/model/phone/SocialPhoneNumLoginQO";

export default class LoginAPI {
    static providerLoginAPI(loginData: UniProviderLoginQO) {
        return socialuniUserRequest.post('socialuni/login/providerLogin', loginData)
    }

    static socialuniPhoneLoginAPI(loginData: UniProviderLoginQO) {
        //接口已作废
        return socialuniUserRequest.post<SocialLoginRO<SocialuniMineUserRO>>('socialuni/login/socialuniPhoneLogin', loginData)
    }

    static phoneLoginAPI(loginData: SocialPhoneNumLoginQO) {
        return socialuniUserRequest.post<SocialLoginRO<SocialuniMineUserRO>>('socialuni/login/phoneLogin', loginData)
    }

    /**
     * 刷新token
     * @param loginData
     */
    static refreshToken() {
        return socialuniUserRequest.post<SocialLoginRO<SocialuniMineUserRO>>('socialuni/login/refreshToken', {});
    }
}
