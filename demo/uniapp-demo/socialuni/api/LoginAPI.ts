import ProviderUserVO from '@/model/login/ProviderUserVO'
import socialHttp from '@/socialuni/http/socialHttp'
import UniProviderLoginQO from '@/model/UniProviderLoginQO'
import SocialLoginRO from '@/model/social/SocialLoginRO'
import SocialPhoneNumLoginQO from '@/model/phone/SocialPhoneNumLoginQO'
import CenterUserDetailRO from '@/model/social/CenterUserDetailRO'


export default class LoginAPI {
  static providerLoginAPI (loginData: UniProviderLoginQO) {
    return socialHttp.post<SocialLoginRO<CenterUserDetailRO>>('login/providerLogin', loginData)
  }

  static phoneLoginAPI (loginData: SocialPhoneNumLoginQO) {
    return socialHttp.post<SocialLoginRO<CenterUserDetailRO>>('login/phoneLogin', loginData)
  }

  //微信绑定手机号使用
  static bindPhoneNumAPI (loginData: ProviderUserVO) {
    return socialHttp.post<CenterUserDetailRO>('user/bindPhoneNumByWx', loginData)
  }
}
