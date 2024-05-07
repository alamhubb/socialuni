import socialuniUserRequest from "../request/socialuniUserRequest";
import UniProviderLoginQO from "socialuni-api-base/src/model/login/UniProviderLoginQO";
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import SocialSendAuthCodeQO from "socialuni-api-base/src/model/phone/SocialSendAuthCodeQO";
import SocialPhoneNumLoginQO from "socialuni-api-base/src/model/phone/SocialPhoneNumLoginQO";
import SocialuniMineUserRO from "socialuni-api-base/src/model/user/SocialuniMineUserRO";
import BindWxPhoneNumQO from "socialuni-api-base/src/model/phone/BindWxPhoneNumQO";
import RefreshWxSessionKeyQO from "socialuni-api-base/src/model/phone/RefreshWxSessionKeyQO";


export default class PhoneAPI {
    static bindSocialuniPhoneNum(loginData: UniProviderLoginQO) {
        return socialuniUserRequest.post<CenterUserDetailRO>('socialuni/phone/bindSocialuniPhoneNum', loginData)
    }

    static sendAuthCodeAPI(phoneNum: string) {
        return socialuniUserRequest.post('socialuni/phone/sendAuthCode', new SocialSendAuthCodeQO(phoneNum))
    }

    static checkRegistry(phoneNum: string) {
        return socialuniUserRequest.post('socialuni/phone/checkRegistry', new SocialSendAuthCodeQO(phoneNum))
    }

    static bindPhoneNumAPI(phoneNum: string, password: string, authCode: string) {
        const phoneNumObj: SocialPhoneNumLoginQO = new SocialPhoneNumLoginQO(phoneNum, password, authCode)
        return socialuniUserRequest.post<string>('socialuni/phone/bindPhoneNum', phoneNumObj).then(res => {
            return res.data
        })
    }

    //微信绑定手机号使用
    static bindWxPhoneNumAPI(bindWxPhoneNumQO: BindWxPhoneNumQO) {
        return socialuniUserRequest.post<CenterUserDetailRO>('socialuni/phone/bindWxPhoneNum', bindWxPhoneNumQO)
    }

    //微信绑定手机号使用
    static refreshWxSessionKeyAPI(code: string) {
        return socialuniUserRequest.post('socialuni/phone/refreshWxSessionKey', new RefreshWxSessionKeyQO(code))
    }

    static getMineUserPhoneNumAPI() {
        return socialuniUserRequest.get('socialuni/phone/getMineUserPhoneNum')
    }
}
