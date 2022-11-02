import ToastUtil from '../utils/ToastUtil'
import UniLoginUtil from '../utils/UniLoginUtil'
import PhoneAPI from "socialuni-api/src/api/socialuni/PhoneAPI";
import {socialUserModule} from "../store/store";
import {socialSystemModule} from "socialuni-sdk/src/store/store";

export default class PhoneService {
    static async bindPhoneNum(phoneNum: string, authCode: string) {
        //手机号绑定
        const user = await PhoneAPI.bindPhoneNumAPI(phoneNum, authCode)
        socialUserModule.setUser(user)
    }

    static async bindWxPhoneNum(wxGetPhoneInfoResult: any) {
        if (wxGetPhoneInfoResult.detail.errMsg === 'getPhoneNumber:ok') {
            /**
             * 在回调中调用 wx.login 登录，可能会刷新登录态。此时服务器使用 code 换取的 sessionKey 不是加密时使用的 sessionKey，
             * 导致解密失败。建议开发者提前进行 login；或者在回调中先使用 checkSession 进行登录态检查，避免 login 刷新登录态
             */
            //先执行登录刷新sessionKey
            //再执行绑定手机号
            // cloudID: ""
            // encryptedData: ""
            // errMsg: "getPhoneNumber:ok"
            // iv: ""
            wxGetPhoneInfoResult.detail.code = await UniLoginUtil.getLoginCode(socialSystemModule.mpPlatform)
            const res = await PhoneAPI.bindWxPhoneNumAPI(wxGetPhoneInfoResult.detail)
            socialUserModule.setUser(res.data)
        } else {
            ToastUtil.error('您选择了不绑定')
        }
    }
}
