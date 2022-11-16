import UserService from 'socialuni-sdk/src/service/UserService'
import UniUserUtil from 'socialuni-sdk/src/utils/UniUserUtil'
import ToastUtil from 'socialuni-sdk/src/utils/ToastUtil'
import {socialSystemModule} from "socialuni-sdk/src/store/store";
import Constants from "socialuni-constant/constant/Constant";
import SocialPhoneNumLoginQO from "socialuni-api/src/model/phone/SocialPhoneNumLoginQO";
import SocialuniMineUserAPI from "socialuni-api/src/api/SocialuniMineUserAPI";

export default class LoginService {
    /**
     * 渠道登录的同一方法
     */
    static async providerLogin(provider: string, result: any) {
        console.log(3333)
        console.log(11111111)
        console.log(22222222)
        console.log(22222222)
        console.log(provider)
        if (socialSystemModule.isMpQQ) {
            if (result.detail.errMsg !== Constants.loginSuccess) {
                return ToastUtil.toast('您取消了登录')
            }
        }
        //一行代码就可以获取登录所需要的信息, 还可以配合后台使用，一键登录，记住用户
        const loginQO = await UniUserUtil.getUniProviderLoginQO(provider)

        const {data} = await SocialuniMineUserAPI.providerLoginAPI(loginQO)

        UserService.getMineUserInitDataActionByToken(data)
    }

    static async phoneLogin(phoneNum: string, authCode: string) {
        const phoneBindQO = new SocialPhoneNumLoginQO(phoneNum, authCode)

        const {data} = await SocialuniMineUserAPI.phoneLoginAPI(phoneBindQO)

        UserService.getMineUserInitDataActionByToken(data)
    }

    /*static async mockSocialuniLogin () {
      constant mockRes = await MockAPI.mockOAuthUserInfoAPI()
      constant providerLoginQO: UniProviderLoginQO = new UniProviderLoginQO(mockRes.data.token, null, mockRes.data.user, LoginProvider.socialuni)

      constant { data } = await LoginAPI.providerLoginAPI(providerLoginQO)

      UserService.getMineUserInitDataActionByToken(data)
    }*/
}
