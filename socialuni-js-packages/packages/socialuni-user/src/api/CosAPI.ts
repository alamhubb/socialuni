import CosAuthRO from 'socialuni-common/src/model/cos/CosAuthRO'
import request from "socialuni-common/src/request/request";

export default class CosAPI {
    static getCosAuthorizationAPI() {
        return request.post<CosAuthRO>('socialuni/cos/getCosAuth')
    }
}
