import WebsocketUtil from '../utils/WebsocketUtil'
import SocialuniTokenUtil from '../utils/SocialuniTokenUtil'
import {socialUserModule} from "../store/store";
import SocialLoginRO from "socialuni-api/src/model/social/SocialLoginRO";
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";

export default class UserService {
    //清空用户信息的组合操作
    static clearUserInfoCom() {
        socialUserModule.removeUser()
        WebsocketUtil.websocketClose()
    }

    //获取通知列表
    //连接websocket
    /**
     * 调用后台仅user和user初始化相关信息,通知列表，开启websocket连接
     */
    static getMineUserInitDataActionByToken(loginRO: SocialLoginRO<CenterUserDetailRO>) {
        socialUserModule.setUserAndToken(loginRO)
        //登录之后重连websocket
        WebsocketUtil.websocketClose()
        // socialNotifyModule.queryNotifiesAction()
        // socialChatModule.getChatsAction()
        // appModule.getImgPathAction()
        return loginRO.user
    }

    static async getMineUserInitDataAction() {
        if (socialUserModule.hasToken) {
            await socialUserModule.getMineUserAction()
        }
    }
}
