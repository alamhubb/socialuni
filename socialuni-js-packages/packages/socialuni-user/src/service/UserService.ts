import WebsocketUtil from 'socialuni-user/src/utils/WebsocketUtil'
import SocialLoginRO from "socialuni-user/src/model/social/SocialLoginRO";
import CenterUserDetailRO from "socialuni-user/src/model/social/CenterUserDetailRO";
import {socialUserModule} from "../store/store";

export default class UserService {
    //清空用户信息的组合操作
    static userLogout() {
        socialUserModule.removeUserAndToken()
        // socialNotifyModule.clearNotifies()
        WebsocketUtil.websocketClose()
    }

    static getAppLunchDataByHasUser() {
        /*socialNotifyModule.queryNotifiesAction()
        socialChatModule.initSocialuniChatModule()*/
    }

    //获取通知列表
    //连接websocket
    /**
     * 调用后台仅user和user初始化相关信息,通知列表，开启websocket连接
     */
    static getMineUserInitDataActionByToken(loginRO: SocialLoginRO<CenterUserDetailRO>) {
        socialUserModule.setUserAndToken(loginRO)
        //登录之后重连websocket
        // WebsocketUtil.websocketClose()
        UserService.getAppLunchDataByHasUser()
        // socialChatModule.getChatsAction()
        // appModule.getImgPathAction()
        return loginRO.user
    }
}
