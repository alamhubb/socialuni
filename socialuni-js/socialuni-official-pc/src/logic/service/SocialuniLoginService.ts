import SocialPhoneNumLoginQO from "socialuni-api-base/src/model/phone/SocialPhoneNumLoginQO";
import LoginAPI from "socialuni-user-api/src/api/LoginAPI";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import WebsocketUtil from "socialuni-api-base/src/websocket/WebsocketUtil";

export default class SocialuniLoginService {
    static async phoneLogin(phoneNum: string, authCode: string) {
        const phoneBindQO = new SocialPhoneNumLoginQO(phoneNum, authCode)
        const {data} = await LoginAPI.phoneLoginAPI(phoneBindQO)
        socialuniUserModule.setUserAndToken(data)
        return data.user
    }
}
