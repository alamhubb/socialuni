import socialuniAdminRequest from '../socialuniAdminRequest'
import SocialPhoneNumQO from '@/model/base/SocialPhoneNumQO'
import SocialLoginRO from '@/model/base/SocialLoginRO'
import DevAccountRO from '@/model/base/DevAccountRO'

export default class LoginAPI {
  static loginAPI(user: SocialPhoneNumQO) {
    return socialuniAdminRequest.post<SocialLoginRO<DevAccountRO>>('user/phoneLogin', user).then(res => {
      return res.data
    })
  }

  static secretKeyLoginAPI(secretKey: string) {
    return socialuniAdminRequest.post<SocialLoginRO<DevAccountRO>>('socialuni/admin/secretKeyLogin', { secretKey })
  }

  static logoutAPI() {
    return socialuniAdminRequest.post('user/logout').then((res: any) => {
      return res.data
    })
  }
}
