import request from 'socialuni-api/src/request'
import SocialUserIdentityAuthPreCheckRO from "../../model/RO/user/SocialUserIdentityAuthPreCheckRO";
import SocialUserIdentityAuthQO from "../../model/QO/user/SocialUserIdentityAuthQO";

export default class SocialUserIdentityAPI {
    static userIdentityAuthPreCheckAPI(authQO: SocialUserIdentityAuthQO) {
        return request.post<SocialUserIdentityAuthPreCheckRO>('socialuni/userIdentity/userIdentityAuthPreCheck', authQO)
    }

    static userIdentityAuthAPI(authQO: SocialUserIdentityAuthQO) {
        return request.post<string>('socialuni/socialuni/userIdentity/userIdentityAuth', authQO)
    }
}
