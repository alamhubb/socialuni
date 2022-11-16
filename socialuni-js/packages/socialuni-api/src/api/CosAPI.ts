import CosAuthRO from 'socialuni-api/src/model/cos/CosAuthRO'
import request from "socialuni-api/src/request/request";

export default class CosAPI {
    static getCosAuthorizationAPI() {
        return request.post<CosAuthRO>('socialuni/cos/getCosAuth')
    }
}
