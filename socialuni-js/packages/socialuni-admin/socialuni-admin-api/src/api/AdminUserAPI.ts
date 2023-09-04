import socialuniAdminRequest from '../socialuniAdminRequest'
import DevAccountRO from '@/model/base/DevAccountRO'
import ToastUtil from '@/utils/ToastUtil'

export default class AdminUserAPI {
    static getUserAPI() {
        return socialuniAdminRequest.post('user/getUser')
    }

    static resetSecretKeyAPI() {
        return socialuniAdminRequest.post<string>('user/resetSecretKey')
    }

    static updateDevAccountAPI(user: DevAccountRO) {
        return socialuniAdminRequest.post<DevAccountRO>('user/updateDevAccount', user).then(res => {
            ToastUtil.success('修改成功')
            return res
        })
    }

    static removeUserBanByPhoneNumAPI(phoneNum: string) {
        return socialuniAdminRequest.post('user/removeUserBanByPhoneNum?phoneNum=' + phoneNum).then(res => {
            ToastUtil.success('解封成功')
            return res
        })
    }
}
