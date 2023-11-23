import FastWebsocket, {FastWebsocketConfig} from "fast-socket/src/FastWebsocket";

export interface FastWebRTCConfig extends FastWebsocketConfig {
}

export default class FastWebRTC {
    private websocket: FastWebsocket = null
    private peerConnection: RTCPeerConnection = null;

    static createClient(config: FastWebRTCConfig) {
        const configuration = {iceServers: [{urls: 'stun:stun.l.google.com:19302'}]};
        const fastWebRTC = new FastWebRTC()
        fastWebRTC.peerConnection = new RTCPeerConnection(configuration)

        config.onMessage = (res) => {
            const data = JSON.parse(res.data);
            console.log(data)
            console.log(7878798789)
            console.log(fastWebRTC.peerConnection.iceConnectionState)
            if (data.offer) {
                console.log(data.offer)
                // 收到远程描述，设置远程描述并创建应答
                fastWebRTC.peerConnection.setRemoteDescription(new RTCSessionDescription(data.offer))
                    .then(() => fastWebRTC.peerConnection.createAnswer())
                    .then(answer => fastWebRTC.peerConnection.setLocalDescription(answer))
                    .then(() => {
                        // 发送本地描述到远程端
                        websocket.send({'answer': fastWebRTC.peerConnection.localDescription});
                    });
            } else if (data.answer) {
                // 收到远程应答
                fastWebRTC.peerConnection.setRemoteDescription(new RTCSessionDescription(data.answer));
            } else if (data.iceCandidate) {
                // 收到ICE candidate，添加到连接中
                fastWebRTC.peerConnection.addIceCandidate(new RTCIceCandidate(data.iceCandidate))
            }
        }
        const websocket = FastWebsocket.createClient(config)


        fastWebRTC.peerConnection.onicecandidate = (event) => {
            websocket.send({'iceCandidate': event.candidate});
        }


        fastWebRTC.websocket = websocket

        return fastWebRTC;
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
