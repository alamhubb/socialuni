import DevAccountRO from '../model/dev/DevAccountRO'
import request from '../../socialuni-common-api/src/request/request';
import OAuthUserInfoQO from '../model/dev/OAuthUserInfoQO'

export default class DevAccountAPI {
    static queryDevAccountAPI(appId: string, provider: string) {
        const queryVO: OAuthUserInfoQO = new OAuthUserInfoQO(appId, provider)
        return request.post<DevAccountRO>('dev/queryDevAccount', queryVO)
    }
}
