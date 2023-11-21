import CosAuthRO from 'socialuni-api-base/src/model/cos/CosAuthRO'
import socialuniAppRequest from "../socialuniAppRequest";

export default class CosAPI {
    static getCosAuthorizationAPI() {
        return socialuniAppRequest.post<CosAuthRO>('socialuni/cos/getCosAuth')
    }

    static getCosPathAPI() {
        return socialuniAppRequest.post<string>('socialuni/cos/getCosPath')
    }
}
