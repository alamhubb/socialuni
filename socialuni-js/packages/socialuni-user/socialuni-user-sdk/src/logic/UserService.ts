import {socialuniUserModule} from "../store/SocialuniUserModule";
import QingAppUtil from "qingjs/src/util/QingAppUtil";

export default class UserService {
    static loginOut() {
        return QingAppUtil.AlertUtil.confirm('是否退出登录').then(() => {
            UserService.clearUserInfo()
            QingAppUtil.ToastUtil.toast('用户退出')
        })
    }

    //清空用户信息的组合操作
    static clearUserInfo() {
        socialuniUserModule.removeUserAndToken()
        // socialChatModule.removeImToken()
        // socialNotifyModule.clearNotifies()
        // WebsocketUtil.websocketClose()
    }
}
