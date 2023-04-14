import UserMsgUtil from "./UserMsgUtil";
import {socialuniUserModule} from "../store/SocialuniUserModule";

export default class CheckUtil {
    static unLoginCheck() {
        if (!socialuniUserModule.mineUser) {
            UserMsgUtil.unLoginMessage()
            Error('未登录')
        }
    }
}
