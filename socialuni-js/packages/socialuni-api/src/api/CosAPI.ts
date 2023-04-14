import CosAuthRO from 'socialuni-base-api/src/model/cos/CosAuthRO'
import request from '../request/request';;

export default class CosAPI {
    static getCosAuthorizationAPI() {
        return request.post<CosAuthRO>('socialuni/cos/getCosAuth')
    }

    static getCosPathAPI() {
        return request.post<string>('socialuni/cos/getCosPath')
    }
}
