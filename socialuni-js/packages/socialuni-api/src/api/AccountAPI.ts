import request from 'socialuni-common/src/request'
import UniProviderLoginQO from '../model/UniProviderLoginQO'


export default class AccountAPI {
    static bindSocialuniAccountAPI(loginData: UniProviderLoginQO) {
        return request.post<void>('account/bindSocialuniAccount', loginData)
    }
}
