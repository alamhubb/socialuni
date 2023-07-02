import socialuniUserRequest from "../request/socialuniUserRequest";
import SocialUserIdentityAuthQO from "@socialuni/socialuni-api-base/src/model/QO/user/SocialUserIdentityAuthQO";
import SocialUserIdentityAuthPreCheckRO from "@socialuni/socialuni-api-base/src/model/RO/user/SocialUserIdentityAuthPreCheckRO";


export default class SocialUserIdentityAPI {
    static userIdentityAuthPreCheckAPI(authQO: SocialUserIdentityAuthQO) {
        return socialuniUserRequest.post<SocialUserIdentityAuthPreCheckRO>('socialuni/userIdentity/userIdentityAuthPreCheck', authQO)
    }

    static userIdentityAuthAPI(authQO: SocialUserIdentityAuthQO) {
        return socialuniUserRequest.post<string>('socialuni/socialuni/userIdentity/userIdentityAuth', authQO)
    }
}
