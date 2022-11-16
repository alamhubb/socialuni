import {socialUserModule} from "socialuni-sdk/src/store/store";
import MsgUtil from "./MsgUtil";

export default class CheckUtil {
    static unLoginCheck() {
        if (!socialUserModule.user) {
            MsgUtil.unLoginMessage()
            Error('未登录')
        }
    }
}
