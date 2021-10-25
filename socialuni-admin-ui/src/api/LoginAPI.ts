import request from '@/plugins/request'
import SocialPhoneNumQO from '@/model/base/SocialPhoneNumQO'
import SocialLoginRO from '@/model/base/SocialLoginRO'
import DevAccountRO from '@/model/base/DevAccountRO'

export default class LoginAPI {
    static loginAPI(user: SocialPhoneNumQO) {
        return request.post<SocialLoginRO<DevAccountRO>>('user/phoneLogin', user).then(res => {
            return res.data
        })
    }

    static secretKeyLoginAPI(secretKey: string) {
        return request.post<SocialLoginRO<DevAccountRO>>('user/secretKeyLogin', {secretKey}).then(res => {
            return res.data
        })
    }

    static logoutAPI() {
        return request.post('user/logout').then((res: any) => {
            return res.data
        })
    }
}
