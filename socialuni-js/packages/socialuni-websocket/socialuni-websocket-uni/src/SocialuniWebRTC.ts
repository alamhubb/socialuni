import WebsocketWebRtcUtil from "socialuni-api-base/src/websocket/WebsocketWebRtcUtil";
import SocialuniWebRTCClient from "./SocialuniWebRTCClient";


interface SocialuniWebsocketConfig {
    wsUrl: string
}

export default class SocialuniWebsocket {
    static createClient() {
        const socialuniWebRTCClient = new SocialuniWebRTCClient()


        const configuration = {iceServers: [{urls: 'stun:stun.l.google.com:19302'}]};


        socialuniWebRTCClient.peerConnection = new RTCPeerConnection(configuration)

        socialuniWebRTCClient.peerConnection.onicecandidate = (event) => {
            WebsocketWebRtcUtil.send({'iceCandidate': event.candidate});
        }

        socialuniWebRTCClient.onicecandidate((event) => {
            if (event.candidate) {
                // 发送ICE候选项到信令服务器
                WebsocketWebRtcUtil.send({'iceCandidate': event.candidate});
            }
        })

        return socialuniWebRTCClient;
    }
}
