import WebsocketUtil from "socialuni-api-base/src/websocket/WebsocketUtil";
import WebsocketWebRtcUtil from "socialuni-api-base/src/websocket/WebsocketWebRtcUtil";
import {socialuniTokenModule} from "socialuni-user-sdk/src/store/SocialuniTokenModule";
import UUIDUtil from "qing-util/src/util/UUIDUtil";
import SocialuniAPIConfig from "socialuni-api-base/src/SocialuniAPIConfig";
import FastWebRTC from "fast-webrtc/src/FastWebRTC";

export default class SocialuniAppService {
    static async getAppLunchData() {
        let token: string = socialuniTokenModule.token

        if (!token) {
            token = UUIDUtil.getUUID()
        }
        WebsocketUtil.createWebsocket()
        /*const websocketUrl = SocialuniAPIConfig.socialuniWebsocketUrl + '/webrtc?token=' + token

        WebsocketWebRtcUtil.easyWebRTC = FastWebRTC.createClient({
            wsUrl: websocketUrl
        })
// 设置远程视频流到video元素
        WebsocketWebRtcUtil.easyWebRTC.ontrack((event) => {
            WebsocketWebRtcUtil.remoteVideo.srcObject = event.streams[0];
        })*/





        // TODO: Implement WebSocket or other technology to receive messages from server
        // and call handleSignalingData when a message is received
        // socialChatModule.initSocialuniChatModule()
        /*socialNotifyModule.queryNotifiesAction()
        */
        // socialChatModule.initSocialuniChatModule()
    }
}
