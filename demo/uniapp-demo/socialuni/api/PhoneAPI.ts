import socialHttp from '/socialuni/plugins/http/socialHttp'
import CenterUserDetailRO from '/socialuni/model/social/CenterUserDetailRO'
import SocialPhoneNumLoginQO from '/socialuni/model/phone/SocialPhoneNumLoginQO'

import RefreshWxSessionKeyQO from '/socialuni/model/phone/RefreshWxSessionKeyQO'
import BindWxPhoneNumQO from '/socialuni/model/phone/BindWxPhoneNumQO'
import SocialSendAuthCodeQO from '/socialuni/model/phone/SocialSendAuthCodeQO'

export default class PhoneAPI {
  static bindSocialPhoneNum () {
    return socialHttp.post<CenterUserDetailRO>('phone/bindSocialPhoneNum')
  }

  static sendAuthCodeAPI (phoneNum: string) {
    return socialHttp.post('phone/sendPhoneAuthCode', new SocialSendAuthCodeQO(phoneNum))
  }

  static bindPhoneNumAPI (phoneNum: string, authCode: string) {
    const phoneNumObj: SocialPhoneNumLoginQO = new SocialPhoneNumLoginQO(phoneNum, authCode)
    return socialHttp.post<CenterUserDetailRO>('phone/bindPhoneNum', phoneNumObj).then(res => {
      return res.data
    })
  }

  //微信绑定手机号使用
  static bindWxPhoneNumAPI (bindWxPhoneNumQO: BindWxPhoneNumQO) {
    return socialHttp.post<CenterUserDetailRO>('phone/bindWxPhoneNum', bindWxPhoneNumQO)
  }

  //微信绑定手机号使用
  static refreshWxSessionKeyAPI (code: string) {
    return socialHttp.post('phone/refreshWxSessionKey', new RefreshWxSessionKeyQO(code))
  }
}
