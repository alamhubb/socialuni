import SocialPhoneNumLoginQO from "socialuni-api-base/src/model/phone/SocialPhoneNumLoginQO";
import LoginAPI from "socialuni-user-api/src/api/LoginAPI";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import Constants from "socialuni-constant/constant/Constant";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import UniUserUtil from "../util/UniUserUtil";
import UserService from "./UserService";
import SocialLoginRO from "socialuni-api-base/src/model/social/SocialLoginRO";
import SocialuniUserRO from "socialuni-api-base/src/model/user/SocialuniUserRO";
import SocialuniUserEventEmit from "../event/SocialuniUserEventEmit";

export default class SocialuniLoginService {
    static async phoneLogin(phoneNum: string, authCode?: string) {
        const phoneBindQO = new SocialPhoneNumLoginQO(phoneNum, null, authCode)
        const {data} = await LoginAPI.phoneLoginAPI(phoneBindQO)
        this.loginSuccess(data)
        return data.user
    }

    static async passwordLogin(phoneNum: string, password: string) {
        const phoneBindQO = new SocialPhoneNumLoginQO(phoneNum, password)
        const {data} = await LoginAPI.passwordLoginAPI(phoneBindQO)
        this.loginSuccess(data)
        return data
    }

    static async phonePasswordLogin(phoneNum: string, password: string, authCode: string) {
        const phoneBindQO = new SocialPhoneNumLoginQO(phoneNum, password, authCode)
        const {data} = await LoginAPI.phonePasswordLoginAPI(phoneBindQO)
        this.loginSuccess(data)
        return data
    }

    /**
     * 渠道登录的同一方法
     */
    static async providerLogin(provider: string, result: any) {
        if (socialuniSystemModule.isMpQQ) {
            if (result.detail.errMsg !== Constants.loginSuccess) {
                return QingAppUtil.ToastUtil.toast('您取消了登录')
            }
        }
        //一行代码就可以获取登录所需要的信息, 还可以配合后台使用，一键登录，记住用户
        const loginQO = await UniUserUtil.getUniProviderLoginQO(provider)

        const {data} = await LoginAPI.providerLoginAPI(loginQO)
        this.loginSuccess(data)
        return data
        // UserService.getMineUserInitDataActionByToken(data)
    }

    private static loginSuccess(userRO: SocialLoginRO<SocialuniUserRO>) {
        socialuniUserModule.setUserAndToken(userRO)
        SocialuniUserEventEmit.loginSuccess()
    }

    /*static async mockSocialuniLogin () {
  constant mockRes = await MockAPI.mockOAuthUserInfoAPI()
  constant providerLoginQO: UniProviderLoginQO = new UniProviderLoginQO(mockRes.data.token, null, mockRes.data.user, LoginProvider.socialuni)

  constant { data } = await LoginAPI.providerLoginAPI(providerLoginQO)

  UserService.getMineUserInitDataActionByToken(data)
}*/
}
