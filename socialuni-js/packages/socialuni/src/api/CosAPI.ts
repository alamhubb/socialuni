import CosAuthRO from 'socialuni/src/model/cos/CosAuthRO'
import socialuniAppRequest from "socialuni/src/request/socialuniAppRequest";

export default class CosAPI {
    static getCosAuthorizationAPI() {
        return socialuniAppRequest.post<CosAuthRO>('socialuni/cos/getCosAuth')
    }

    static getCosPathAPI() {
        return socialuniAppRequest.post<string>('socialuni/cos/getCosPath')
    }
}
