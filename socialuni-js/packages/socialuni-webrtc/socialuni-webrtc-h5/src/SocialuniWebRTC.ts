import WebsocketWebRtcUtil from "socialuni-api-base/src/websocket/WebsocketWebRtcUtil";
import SocialuniWebsocket, {SocialuniWebsocketConfig} from "socialuni-websocket-h5/src/SocialuniWebsocket";


export interface SocialuniWebRTCConfig extends SocialuniWebsocketConfig {
}

export default class SocialuniWebRTC {
    private websocket: SocialuniWebsocket = null
    private peerConnection: RTCPeerConnection = null;

    static createClient(config: SocialuniWebRTCConfig) {
        const configuration = {iceServers: [{urls: 'stun:stun.l.google.com:19302'}]};
        const socialuniWebRTC = new SocialuniWebRTC()
        socialuniWebRTC.peerConnection = new RTCPeerConnection(configuration)

        config.onMessage = (res) => {
            const data = JSON.parse(res.data);
            console.log(data)
            console.log(7878798789)
            console.log(socialuniWebRTC.peerConnection.iceConnectionState)
            if (data.offer) {
                // 收到远程描述，设置远程描述并创建应答
                socialuniWebRTC.peerConnection.setRemoteDescription(new RTCSessionDescription(data.offer))
                    .then(() => socialuniWebRTC.peerConnection.createAnswer())
                    .then(answer => socialuniWebRTC.peerConnection.setLocalDescription(answer))
                    .then(() => {
                        // 发送本地描述到远程端
                        websocket.send({'answer': socialuniWebRTC.peerConnection.localDescription});
                    });
            } else if (data.answer) {
                // 收到远程应答
                socialuniWebRTC.peerConnection.setRemoteDescription(new RTCSessionDescription(data.answer));
            } else if (data.iceCandidate) {
                // 收到ICE candidate，添加到连接中
                socialuniWebRTC.peerConnection.addIceCandidate(new RTCIceCandidate(data.iceCandidate))
            }
        }
        const websocket = SocialuniWebsocket.createClient(config)


        socialuniWebRTC.peerConnection.onicecandidate = (event) => {
            websocket.send({'iceCandidate': event.candidate});
        }


        socialuniWebRTC.websocket = websocket

        return socialuniWebRTC;
    }

    ontrack(callback: (ev: RTCTrackEvent) => any) {
        this.peerConnection.ontrack = (event) => {
            callback(event)
        };
    }

    /*
        addTrack(track: MediaStreamTrack, ...streams: MediaStream[]) {
            return this.peerConnection.addTrack(track, ...streams)
        }
    */

    async pushStream(mediaStream: MediaStream) {
        //将流放到rtc中
        mediaStream.getTracks().forEach(track => {
            this.peerConnection.addTrack(track, mediaStream);
        });

        //建立信道
        const offer = await this.peerConnection.createOffer()

        //设置信道
        await this.peerConnection.setLocalDescription(offer)

        // 发送本地描述到远程端
        this.websocket.send({'offer': this.peerConnection.localDescription});
    }
}
