import {socialuniUserModule} from "socialuni-sdk/src/store/store";
import MsgUtil from "./MsgUtil";

export default class CheckUtil {
    static unLoginCheck() {
        if (!socialuniUserModule.mineUser) {
            MsgUtil.unLoginMessage()
            Error('未登录')
        }
    }
}
