import WebsocketUtil from "@socialuni/socialuni-api-base/src/websocket/WebsocketUtil";
import WebsocketWebRtcUtil from "@socialuni/socialuni-api-base/src/websocket/WebsocketWebRtcUtil";

export default class SocialuniAppService {
    static async getAppLunchData() {
        /* && !socialChatModule.imToken   // 修复imToken过期后，需要重新登录的才能聊天的bug。 https://gitee.com/socialuni/socialuni/issues/I6GGP7
        * */
        // const imRes = await SocialuniImUserAPI.getImUserTokenAPI()
        // socialChatModule.setImToken(imRes.data)
        WebsocketUtil.websocketConnect(false)
        WebsocketWebRtcUtil.websocketConnect(false)
        // socialChatModule.initSocialuniChatModule()
        /*socialNotifyModule.queryNotifiesAction()
        */
        // socialChatModule.initSocialuniChatModule()
    }
}
