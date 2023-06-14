import request from '@/plugins/request'
import DevAccountRO from '@/model/base/DevAccountRO'
import ToastUtil from '@/utils/ToastUtil'

export default class AdminUserAPI {
    static getUserAPI() {
        return request.post('user/getUser')
    }

    static resetSecretKeyAPI() {
        return request.post<string>('user/resetSecretKey')
    }

    static updateDevAccountAPI(user: DevAccountRO) {
        return request.post<DevAccountRO>('user/updateDevAccount', user).then(res => {
            SocialuniAppUtil.ToastUtil.success('修改成功')
            return res
        })
    }

    static removeUserBanByPhoneNumAPI(phoneNum: string) {
        return request.post('user/removeUserBanByPhoneNum?phoneNum=' + phoneNum).then(res => {
            SocialuniAppUtil.ToastUtil.success('解封成功')
            return res
        })
    }
}
