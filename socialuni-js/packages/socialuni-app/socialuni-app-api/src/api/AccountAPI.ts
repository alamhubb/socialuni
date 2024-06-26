import request from '../request/request';
import UniProviderLoginQO from '../../src/model/login/UniProviderLoginQO'


export default class AccountAPI {
    static bindSocialuniAccountAPI(loginData: UniProviderLoginQO) {
        return request.post<void>('account/bindSocialuniAccount', loginData)
    }
}
