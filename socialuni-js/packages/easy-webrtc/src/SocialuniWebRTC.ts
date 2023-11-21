import EasyWebsocket, {EasyWebsocketConfig} from "easy-websocket/src/SocialuniWebsocket";

export interface EasyWebRTCConfig extends EasyWebsocketConfig {
}

export default class EasyWebRTC {
    private websocket: EasyWebsocket = null
    private peerConnection: RTCPeerConnection = null;

    static createClient(config: EasyWebRTCConfig) {
        const configuration = {iceServers: [{urls: 'stun:stun.l.google.com:19302'}]};
        const easyWebRTC = new EasyWebRTC()
        easyWebRTC.peerConnection = new RTCPeerConnection(configuration)

        config.onMessage = (res) => {
            const data = JSON.parse(res.data);
            console.log(data)
            console.log(7878798789)
            console.log(easyWebRTC.peerConnection.iceConnectionState)
            if (data.offer) {
                // 收到远程描述，设置远程描述并创建应答
                easyWebRTC.peerConnection.setRemoteDescription(new RTCSessionDescription(data.offer))
                    .then(() => easyWebRTC.peerConnection.createAnswer())
                    .then(answer => easyWebRTC.peerConnection.setLocalDescription(answer))
                    .then(() => {
                        // 发送本地描述到远程端
                        websocket.send({'answer': easyWebRTC.peerConnection.localDescription});
                    });
            } else if (data.answer) {
                // 收到远程应答
                easyWebRTC.peerConnection.setRemoteDescription(new RTCSessionDescription(data.answer));
            } else if (data.iceCandidate) {
                // 收到ICE candidate，添加到连接中
                easyWebRTC.peerConnection.addIceCandidate(new RTCIceCandidate(data.iceCandidate))
            }
        }
        const websocket = EasyWebsocket.createClient(config)


        easyWebRTC.peerConnection.onicecandidate = (event) => {
            websocket.send({'iceCandidate': event.candidate});
        }


        easyWebRTC.websocket = websocket

        return easyWebRTC;
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
