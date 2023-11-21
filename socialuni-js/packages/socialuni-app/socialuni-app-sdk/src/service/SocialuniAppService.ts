import WebsocketUtil from "socialuni-api-base/src/websocket/WebsocketUtil";
import WebsocketWebRtcUtil from "socialuni-api-base/src/websocket/WebsocketWebRtcUtil";
import JsonUtil from "socialuni-util/src/util/JsonUtil";
import test1 from './test1.mp3'

export default class SocialuniAppService {
    static async getAppLunchData() {
        /* && !socialChatModule.imToken   // 修复imToken过期后，需要重新登录的才能聊天的bug。 https://gitee.com/socialuni/socialuni/issues/I6GGP7
        * */
        // const imRes = await SocialuniImUserAPI.getImUserTokenAPI()
        // socialChatModule.setImToken(imRes.data)
        const configuration = {iceServers: [{urls: 'stun:stun.l.google.com:19302'}]};

        //新建rtc
        WebsocketWebRtcUtil.peerConnection = new RTCPeerConnection(configuration);
// 设置远程视频流到video元素
        WebsocketWebRtcUtil.peerConnection.ontrack = (event) => {
            console.log(event)
            WebsocketWebRtcUtil.remoteVideo.srcObject = event.streams[0];
        };

        WebsocketWebRtcUtil.peerConnection.oniceconnectionstatechange = (event) => {
            console.log(event);
            console.log('触发了状态变化')
            console.log('ICE connection state change:', WebsocketWebRtcUtil.peerConnection.iceConnectionState);
        };

        WebsocketWebRtcUtil.peerConnection.onicecandidate = (event) => {
            if (event.candidate) {
                // 发送ICE候选项到信令服务器
                WebsocketWebRtcUtil.send({'iceCandidate': event.candidate});
            }
        };


        WebsocketUtil.websocketConnect(false)
        WebsocketWebRtcUtil.websocketConnect(false)

        // TODO: Implement WebSocket or other technology to receive messages from server
        // and call handleSignalingData when a message is received
        // socialChatModule.initSocialuniChatModule()
        /*socialNotifyModule.queryNotifiesAction()
        */
        // socialChatModule.initSocialuniChatModule()
    }
}
