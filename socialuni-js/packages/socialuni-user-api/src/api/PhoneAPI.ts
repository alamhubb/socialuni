import socialuniAppRequest from "socialuni/src/request/socialuniAppRequest";
import UniProviderLoginQO from "socialuni/src/model/login/UniProviderLoginQO";
import CenterUserDetailRO from "socialuni/src/model/social/CenterUserDetailRO";
import SocialSendAuthCodeQO from "socialuni/src/model/phone/SocialSendAuthCodeQO";
import SocialPhoneNumLoginQO from "socialuni/src/model/phone/SocialPhoneNumLoginQO";
import SocialuniMineUserRO from "socialuni/src/model/user/SocialuniMineUserRO";
import BindWxPhoneNumQO from "socialuni/src/model/phone/BindWxPhoneNumQO";
import RefreshWxSessionKeyQO from "socialuni/src/model/phone/RefreshWxSessionKeyQO";


export default class PhoneAPI {
  static bindSocialuniPhoneNum (loginData: UniProviderLoginQO) {
    return socialuniAppRequest.post<CenterUserDetailRO>('socialuni/phone/bindSocialuniPhoneNum', loginData)
  }

  static sendAuthCodeAPI (phoneNum: string) {
    return socialuniAppRequest.post('socialuni/phone/sendAuthCode', new SocialSendAuthCodeQO(phoneNum))
  }

  static bindPhoneNumAPI (phoneNum: string, authCode: string) {
    const phoneNumObj: SocialPhoneNumLoginQO = new SocialPhoneNumLoginQO(phoneNum, authCode)
    return socialuniAppRequest.post<SocialuniMineUserRO>('socialuni/phone/bindPhoneNum', phoneNumObj).then(res => {
      return res.data
    })
  }

  //微信绑定手机号使用
  static bindWxPhoneNumAPI (bindWxPhoneNumQO: BindWxPhoneNumQO) {
    return socialuniAppRequest.post<CenterUserDetailRO>('socialuni/phone/bindWxPhoneNum', bindWxPhoneNumQO)
  }

  //微信绑定手机号使用
  static refreshWxSessionKeyAPI (code: string) {
    return socialuniAppRequest.post('socialuni/phone/refreshWxSessionKey', new RefreshWxSessionKeyQO(code))
  }
}
