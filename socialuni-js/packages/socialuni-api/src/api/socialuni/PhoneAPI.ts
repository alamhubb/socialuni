import CenterUserDetailRO from '../../model/social/CenterUserDetailRO'
import SocialPhoneNumLoginQO from '../../model/phone/SocialPhoneNumLoginQO'
import RefreshWxSessionKeyQO from '../../model/phone/RefreshWxSessionKeyQO'
import BindWxPhoneNumQO from '../../model/phone/BindWxPhoneNumQO'
import SocialSendAuthCodeQO from '../../model/phone/SocialSendAuthCodeQO'
import request from "socialuni-api/src/request/request";
import UniProviderLoginQO from "../../model/login/UniProviderLoginQO";

export default class PhoneAPI {
  static bindSocialuniPhoneNum (loginData: UniProviderLoginQO) {
    return request.post<CenterUserDetailRO>('socialuni/phone/bindSocialuniPhoneNum', loginData)
  }

  static sendAuthCodeAPI (phoneNum: string) {
    return request.post('socialuni/phone/sendAuthCode', new SocialSendAuthCodeQO(phoneNum))
  }

  static bindPhoneNumAPI (phoneNum: string, authCode: string) {
    const phoneNumObj: SocialPhoneNumLoginQO = new SocialPhoneNumLoginQO(phoneNum, authCode)
    return request.post<CenterUserDetailRO>('socialuni/phone/bindPhoneNum', phoneNumObj).then(res => {
      return res.data
    })
  }

  //微信绑定手机号使用
  static bindWxPhoneNumAPI (bindWxPhoneNumQO: BindWxPhoneNumQO) {
    return request.post<CenterUserDetailRO>('socialuni/phone/bindWxPhoneNum', bindWxPhoneNumQO)
  }

  //微信绑定手机号使用
  static refreshWxSessionKeyAPI (code: string) {
    return request.post('socialuni/phone/refreshWxSessionKey', new RefreshWxSessionKeyQO(code))
  }
}
