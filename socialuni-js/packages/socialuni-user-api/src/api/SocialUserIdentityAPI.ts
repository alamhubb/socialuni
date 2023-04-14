import socialuniAppRequest from "socialuni-base-api/src/request/socialuniAppRequest";
import SocialUserIdentityAuthQO from "socialuni-base-api/src/model/QO/user/SocialUserIdentityAuthQO";
import SocialUserIdentityAuthPreCheckRO from "socialuni-base-api/src/model/RO/user/SocialUserIdentityAuthPreCheckRO";


export default class SocialUserIdentityAPI {
    static userIdentityAuthPreCheckAPI(authQO: SocialUserIdentityAuthQO) {
        return socialuniAppRequest.post<SocialUserIdentityAuthPreCheckRO>('socialuni/userIdentity/userIdentityAuthPreCheck', authQO)
    }

    static userIdentityAuthAPI(authQO: SocialUserIdentityAuthQO) {
        return socialuniAppRequest.post<string>('socialuni/socialuni/userIdentity/userIdentityAuth', authQO)
    }
}
