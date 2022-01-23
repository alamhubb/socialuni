import request from '../plugins/http/request'
import CenterUserDetailRO from '../model/social/CenterUserDetailRO'
import SocialPhoneNumLoginQO from '../model/phone/SocialPhoneNumLoginQO'

import RefreshWxSessionKeyQO from '../model/phone/RefreshWxSessionKeyQO'
import BindWxPhoneNumQO from '../model/phone/BindWxPhoneNumQO'
import SocialSendAuthCodeQO from '../model/phone/SocialSendAuthCodeQO'
import UniProviderLoginQO from '@/socialuni/model/UniProviderLoginQO'

export default class PhoneAPI {
  static bindSocialuniPhoneNum (loginData: UniProviderLoginQO) {
    return request.post<CenterUserDetailRO>('phone/bindSocialuniPhoneNum', loginData)
  }

  static sendAuthCodeAPI (phoneNum: string) {
    return request.post('phone/sendPhoneAuthCode', new SocialSendAuthCodeQO(phoneNum))
  }

  static bindPhoneNumAPI (phoneNum: string, authCode: string) {
    const phoneNumObj: SocialPhoneNumLoginQO = new SocialPhoneNumLoginQO(phoneNum, authCode)
    return request.post<CenterUserDetailRO>('phone/bindPhoneNum', phoneNumObj).then(res => {
      return res.data
    })
  }

  //微信绑定手机号使用
  static bindWxPhoneNumAPI (bindWxPhoneNumQO: BindWxPhoneNumQO) {
    return request.post<CenterUserDetailRO>('phone/bindWxPhoneNum', bindWxPhoneNumQO)
  }

  //微信绑定手机号使用
  static refreshWxSessionKeyAPI (code: string) {
    return request.post('phone/refreshWxSessionKey', new RefreshWxSessionKeyQO(code))
  }
}
