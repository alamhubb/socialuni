import SocialLoginRO from "socialuni-api-base/src/model/social/SocialLoginRO";
import SocialuniMineUserRO from "socialuni-api-base/src/model/user/SocialuniMineUserRO";
import WebsocketUtil from "socialuni-api-base/src/websocket/WebsocketUtil";
import {socialuniUserModule} from "../store/SocialuniUserModule";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import SocialuniAppService from "socialuni-app-sdk/src/service/SocialuniAppService";

export default class UserService {
    static async getAppLunchData() {
        /* && !socialChatModule.imToken   // 修复imToken过期后，需要重新登录的才能聊天的bug。 https://gitee.com/socialuni/socialuni/issues/I6GGP7
        * */
        // const imRes = await SocialuniImUserAPI.getImUserTokenAPI()
        // socialChatModule.setImToken(imRes.data)
        SocialuniAppService.getAppLunchData()
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
        // UserService.getAppLunchDataByHasUser()
        UserService.getAppLunchData()
        // socialChatModule.getChatsAction()
        // appModule.getImgPathAction()
        return loginRO.user
    }

    static loginOut() {
        return QingAppUtil.AlertUtil.confirm('是否退出登录').then(() => {
            UserService.clearUserInfo()
            QingAppUtil.ToastUtil.toast('用户退出')
        })
    }

    //清空用户信息的组合操作
    static clearUserInfo() {
        socialuniUserModule.removeUserAndToken()
        UserService.getAppLunchData()
        // socialChatModule.removeImToken()
        // socialNotifyModule.clearNotifies()
        // WebsocketUtil.websocketClose()
    }
}
