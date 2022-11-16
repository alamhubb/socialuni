import WebsocketUtil from 'socialuni-sdk/src/utils/WebsocketUtil'
import SocialLoginRO from "socialuni-sdk/src/model/social/SocialLoginRO";
import CenterUserDetailRO from "socialuni-sdk/src/model/social/CenterUserDetailRO";
import {socialUserModule} from "../store/store";
import SocialuniMineUserRO from "../model/user/SocialuniMineUserRO";

export default class UserService {
    //清空用户信息的组合操作
    static userLogout() {
        socialUserModule.removeUserAndToken()
        // socialNotifyModule.clearNotifies()
        WebsocketUtil.websocketClose()
    }

    static getAppLunchDataByHasUser() {
        /*socialNotifyModule.queryNotifiesAction()
        */
        // socialChatModule.initSocialuniChatModule()
    }

    //获取通知列表
    //连接websocket
    /**
     * 调用后台仅user和user初始化相关信息,通知列表，开启websocket连接
     */
    static getMineUserInitDataActionByToken(loginRO: SocialLoginRO<SocialuniMineUserRO>) {
        socialUserModule.setUserAndToken(loginRO)
        //登录之后重连websocket
        // WebsocketUtil.websocketClose()
        UserService.getAppLunchDataByHasUser()
        // socialChatModule.getChatsAction()
        // appModule.getImgPathAction()
        return loginRO.user
    }
}
