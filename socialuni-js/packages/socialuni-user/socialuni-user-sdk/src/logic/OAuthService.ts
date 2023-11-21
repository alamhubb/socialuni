import ResultRO from "socialuni-api-base/src/model/social/ResultRO";
import SocialLoginRO from "socialuni-api-base/src/model/social/SocialLoginRO";
import UniUserInfoRO from "socialuni-api-base/src/model/login/UniUserInfoRO";
import {socialuniUserModule} from "../store/SocialuniUserModule";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import UniProviderLoginQO from "socialuni-api-base/src/model/login/UniProviderLoginQO";
import LoginProvider from "socialuni-constant/constant/LoginProvider";
import LoginAPI from "socialuni-user-api/src/api/LoginAPI";
import UserService from "./UserService";
import PhoneAPI from "socialuni-user-api/src/api/PhoneAPI";

export default class OAuthService {
  static async oAuthUserInfo (params: any) {
    if (params && params.referrerInfo) {
      const info = params.referrerInfo
      //这里可以返回回来的appId
      // appModule.threeProviderAppId = info.appId
      const extraData: ResultRO<SocialLoginRO<UniUserInfoRO>> = info.extraData
      if (extraData) {
        if (extraData.success) {
          const authData: SocialLoginRO<UniUserInfoRO> = extraData.data
          if (socialuniUserModule.hasUser) {
            await OAuthService.oAuthBindSocialuniPhone(authData)
          } else {
            await OAuthService.oAuthUserPhoneNumLogin(authData)
          }
          SocialuniAppUtil.ToastUtil.toastLong('授权成功')
          // PageUtil.toMinePage()
        }
      }
    }
  }

  static async oAuthUserPhoneNumLogin (oAuthRO: SocialLoginRO<UniUserInfoRO>) {
    const providerLoginQO: UniProviderLoginQO = new UniProviderLoginQO(oAuthRO.token, null, oAuthRO.user, LoginProvider.socialuni)

    const { data } = await LoginAPI.socialuniPhoneLoginAPI(providerLoginQO)

    UserService.getMineUserInitDataActionByToken(data)
  }

  //绑定清池手机号，需要已绑定清池手机号
  static async oAuthBindSocialuniPhone (oAuthRO: SocialLoginRO<UniUserInfoRO>) {
    const providerLoginQO: UniProviderLoginQO = new UniProviderLoginQO(oAuthRO.token, null, oAuthRO.user, LoginProvider.socialuni)

    const { data } = await PhoneAPI.bindSocialuniPhoneNum(providerLoginQO)

    socialuniUserModule.setUser(data)
  }
}
