import WebsocketUtil from 'socialuni-common/src/utils/WebsocketUtil'
import {socialNotifyModule} from "socialuni-sdk/src/store/store";
import SocialLoginRO from "socialuni-api/src/model/social/SocialLoginRO";
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";
import {socialUserModule} from "../store/store";
import {socialChatModule} from "socialuni-im/src/store/store";

export default class UserService {
    //清空用户信息的组合操作
    static userLogout() {
        socialUserModule.removeUserAndToken()
        socialNotifyModule.clearNotifies()
        WebsocketUtil.websocketClose()
    }

    static getAppLunchDataByHasUser() {
        socialNotifyModule.queryNotifiesAction()
        socialChatModule.initSocialuniChatModule()
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
