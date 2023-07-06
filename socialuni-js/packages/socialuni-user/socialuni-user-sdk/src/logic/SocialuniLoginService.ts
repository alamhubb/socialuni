import SocialPhoneNumLoginQO from "@socialuni/socialuni-api-base/src/model/phone/SocialPhoneNumLoginQO";
import LoginAPI from "@socialuni/socialuni-user-api/src/api/LoginAPI";
import {socialuniUserModule} from "@socialuni/socialuni-user-sdk/src/store/SocialuniUserModule";

export default class SocialuniLoginService {
    static async phoneLogin(phoneNum: string, authCode: string) {
        const phoneBindQO = new SocialPhoneNumLoginQO(phoneNum, authCode)
        const {data} = await LoginAPI.phoneLoginAPI(phoneBindQO)
        socialuniUserModule.setUserAndToken(data)
        return data.user
    }
}
