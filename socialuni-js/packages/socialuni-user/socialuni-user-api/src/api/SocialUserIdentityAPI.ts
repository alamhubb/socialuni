import socialuniUserRequest from "../request/socialuniUserRequest";
import SocialUserIdentityAuthQO from "socialuni-api-base/src/model/QO/user/SocialUserIdentityAuthQO";
import SocialUserIdentityAuthPreCheckRO from "socialuni-api-base/src/model/RO/user/SocialUserIdentityAuthPreCheckRO";


export default class SocialUserIdentityAPI {
    static userIdentityAuthPreCheckAPI(authQO: SocialUserIdentityAuthQO) {
        return socialuniUserRequest.post<SocialUserIdentityAuthPreCheckRO>('socialuni/userIdentity/userIdentityAuthPreCheck', authQO)
    }

    static userIdentityAuthAPI(authQO: SocialUserIdentityAuthQO) {
        return socialuniUserRequest.post<string>('socialuni/userIdentity/userIdentityAuth', authQO)
    }

    static getMineUserIdentityStatusAPI() {
        return socialuniUserRequest.get<boolean>('socialuni/userIdentity/getMineUserIdentityStatusAPI')
    }
}
