import SocialPhoneNumLoginQO from "socialuni-api-base/src/model/phone/SocialPhoneNumLoginQO";
import LoginAPI from "socialuni-user-api/src/api/LoginAPI";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";

export default class SocialuniLoginService {
    static async phoneLogin(phoneNum: string, authCode?: string) {
        const phoneBindQO = new SocialPhoneNumLoginQO(phoneNum, null, authCode)
        const {data} = await LoginAPI.phoneLoginAPI(phoneBindQO)
        socialuniUserModule.setUserAndToken(data)
        return data.user
    }

    static async passwordLogin(phoneNum: string, password: string) {
        const phoneBindQO = new SocialPhoneNumLoginQO(phoneNum, password)
        const {data} = await LoginAPI.passwordLoginAPI(phoneBindQO)
        socialuniUserModule.setUserAndToken(data)
        return data.user
    }

    static async phonePasswordLogin(phoneNum: string, password: string, authCode: string) {
        const phoneBindQO = new SocialPhoneNumLoginQO(phoneNum, password, authCode)
        const {data} = await LoginAPI.phonePasswordLoginAPI(phoneBindQO)
        socialuniUserModule.setUserAndToken(data)
        return data.user
    }
}
