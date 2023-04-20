import SocialLoginRO from "socialuni-base-api/src/model/social/SocialLoginRO";
import SocialuniMineUserRO from "socialuni-base-api/src/model/user/SocialuniMineUserRO";
import {socialuniUserModule} from "socialuni/src/store/SocialuniUserModule";
import WebsocketUtil from "socialuni/src/utils/WebsocketUtil";
import AlertUtil from "socialuni-util/src/util/AlertUtil";
import ToastUtil from "socialuni-util/src/util/ToastUtil";

export default class UserService {
    static async getAppLunchDataByHasUser() {
        /* && !socialChatModule.imToken   // 修复imToken过期后，需要重新登录的才能聊天的bug。 https://gitee.com/socialuni/socialuni/issues/I6GGP7
        * */
        // const imRes = await SocialuniImUserAPI.getImUserTokenAPI()
        // socialChatModule.setImToken(imRes.data)
        WebsocketUtil.websocketConnect(false)
        // socialChatModule.initSocialuniChatModule()
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
        socialuniUserModule.setUserAndToken(loginRO)
        //登录之后重连websocket
        // WebsocketUtil.websocketClose()
        UserService.getAppLunchDataByHasUser()
        // socialChatModule.getChatsAction()
        // appModule.getImgPathAction()
        return loginRO.user
    }

    static loginOut() {
        return AlertUtil.confirm('是否退出登录').then(() => {
            UserService.clearUserInfo()
            ToastUtil.toast('用户退出')
        })
    }

    //清空用户信息的组合操作
    static clearUserInfo() {
        socialuniUserModule.removeUserAndToken()
        UserService.getAppLunchDataByHasUser()
        // socialChatModule.removeImToken()
        // socialNotifyModule.clearNotifies()
        // WebsocketUtil.websocketClose()
    }
}
