import SocialLoginRO from "socialuni-api/src/model/social/SocialLoginRO";
import {socialChatModule, socialUserModule} from "../store/store";
import SocialuniImUserAPI from "socialuni-api/src/api/SocialuniImUserAPI"
import AlertUtil from "../utils/AlertUtil";
import ToastUtil from "../utils/ToastUtil";
import SocialuniMineUserRO from "socialuni-api/src/model/user/SocialuniMineUserRO";
import SocialuniUserImAPI from "socialuni-api/src/api/SocialuniImUserAPI";

export default class UserService {
    static async getAppLunchDataByHasUser() {
        if (socialUserModule && socialUserModule.token && !socialChatModule.imToken) {
            const imRes = await SocialuniImUserAPI.getImUserTokenAPI()
            socialChatModule.setImToken(imRes.data)
        }
        socialChatModule.initSocialuniChatModule()
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

    static loginOut() {
        return AlertUtil.confirm('是否退出登录').then(() => {
            UserService.clearUserInfo()
            ToastUtil.toast('用户退出')
        })
    }

    //清空用户信息的组合操作
    static clearUserInfo() {
        socialUserModule.removeUserAndToken()
        socialChatModule.removeImToken()
        // socialNotifyModule.clearNotifies()
        // WebsocketUtil.websocketClose()
    }
}
