import SocialuniUserRO from "socialuni-api-base/src/model/user/SocialuniUserRO";
import {socialuniUserModule} from "../store/SocialuniUserModule";

export default class SocialuniUserUtil {
    static isMine(user: SocialuniUserRO) {
        if (user == null) {
            return false
        }
        const mineUser: SocialuniUserRO = socialuniUserModule.mineUser
        return mineUser.id === user.id
    }
}
