import CommonUtil from "@socialuni/socialuni-util/src/util/CommonUtil";
import NotifyVO from "../model/NotifyVO";
import JsonUtil from "@socialuni/socialuni-util/src/util/JsonUtil";
import UUIDUtil from "@socialuni/socialuni-util/src/util/UUIDUtil";
import SocialuniAPIConfig from "../SocialuniAPIConfig";
import {socialuniSystemModule} from "@socialuni/socialuni-util/src/store/SocialuniSystemModule";
import {socialuniPluginsModule} from "@socialuni/socialuni/src/store/SocialuniPluginsModule";
import {socialuniTokenModule} from "@socialuni/socialuni-user-sdk/src/store/SocialuniTokenModule";


export default class WebsocketWebRtcUtil {
    //失败重连时间
    static failedReconnectTime = 3000
    static pingTime = 10000
    static timer: number = null
    static locking: boolean = false
    static ws: WebSocket = null

    static send(msg: any) {
        this.ws.send(JsonUtil.toJson(msg))
    }

    static reConnect() {
        console.log('触发了重连未执行')
        if (this.locking) return;
        this.locking = true;
        console.log('触发了重连')
        CommonUtil.delayTime(WebsocketWebRtcUtil.failedReconnectTime).then(() => {
            console.log('触发了延时开启')
            WebsocketWebRtcUtil.websocketConnect(true)
        })
    }

    static websocketConnect(reload: boolean) {
        this.locking = false;
        //上锁，防止无限重连，因为会触发close会触发重连
        this.websocketClose()
        this.ws = undefined;

        console.log('websocket连接')

        let token: string = socialuniTokenModule.token

        if (!token) {
            token = UUIDUtil.getUUID()
        }
        const websocketUrl = SocialuniAPIConfig.socialuniWebsocketUrl + '/webrtc?token=' + token

        const onOpen = (() => {
            this.locking = false
            if (reload || WebsocketWebRtcUtil.timer) {
                clearInterval(WebsocketWebRtcUtil.timer)
            }
            if (reload) {
                console.log('重新加载')
            }

            //心跳保活
            WebsocketWebRtcUtil.timer = setInterval(() => {
                if (this.ws.readyState === this.ws.OPEN) {
                    console.log('触发了ping')
                    this.ws.send('ping')
                }
            }, WebsocketWebRtcUtil.pingTime)
        })
        const onError = (() => {
            console.log('触发了错误')
            // #ifndef MP
            this.reConnect()
            // #endif
        })
        const onClose = ((e) => {
            console.log('触发了关闭:' + JsonUtil.log(e))
            this.reConnect()
        })

        // 创建 RTCPeerConnection
        /*const configuration = {iceServers: [{urls: 'stun:stun.l.google.com:19302'}]};
        const peerConnection = new RTCPeerConnection(configuration);

        // 处理接收到的音频轨道
        peerConnection.ontrack = (event) => {
            // event.streams 包含了接收到的音频轨道
            const audioStream = event.streams[0];
            console.log(audioStream)
// 检查流中是否包含音频轨道
            if (audioStream.getAudioTracks().length > 0) {
                const track1 = audioStream.getTracks()[0]
                console.log(audioStream.getAudioTracks())
                console.log(audioStream.getTracks())

                track1.enabled = true; // 确保轨道未被静音


                const audioStream1 = new MediaStream([track1]);

                // 在页面上播放音频（这只是一个示例）
                const audioElement = new Audio();
                audioElement.srcObject = audioStream1;
                audioElement.volume = 1;
                audioElement.muted = false;
                console.log(track1)
                console.log(audioStream)
                console.log(audioStream1)

                audioElement.onerror = (event) => {
                    console.log(event)
                }
                audioElement.oncanplaythrough = ((event) => {
                    console.log(event)
                })

                audioElement.onplay = ((event) => {
                    console.log(event)
                })


                audioElement.play();

                const mediaRecorder = new MediaRecorder(audioStream1);
                const chunks = [];

                mediaRecorder.ondataavailable = (event) => {
                    if (event.data.size > 0) {
                        chunks.push(event.data);
                    }
                };

                mediaRecorder.onstop = () => {
                    const blob = new Blob(chunks, { type: 'audio/mp3' });
                    const url = URL.createObjectURL(blob);

                    // 创建一个下载链接
                    const a = document.createElement('a');
                    a.href = url;
                    a.download = 'recorded.mp3';
                    a.style.display = 'none';
                    document.body.appendChild(a);

                    // 触发点击下载链接
                    a.click();

                    // 清理资源
                    window.URL.revokeObjectURL(url);
                };

// 开始录制
                mediaRecorder.start();

// 停止录制（在需要时调用）
                mediaRecorder.stop();
            } else {
                console.error('Stream does not contain audio tracks');
            }

        };*/


        const peerConnection = new RTCPeerConnection();

        peerConnection.ontrack = function (event) {
            const remoteVideo = document.getElementById('remoteVideo');
            console.log('chufale event')
            console.log(remoteVideo)
            // 当接收到轨道时，将其显示在远程视频元素上
            remoteVideo.srcObject = event.streams[0];
        };

        peerConnection.oniceconnectionstatechange = function (e) {
            console.log('触发了状态变化')
            console.log(e)
        };


        const onMessage = (async (res: MessageEvent) => {
            const data = JSON.parse(res.data);
            console.log(data)
            console.log(7878798789)
            console.log(peerConnection.iceConnectionState)
            if (peerConnection.iceConnectionState === 'stable') {
                if (data.offer) {
                    // 收到远程描述，设置远程描述并创建应答
                    peerConnection.setRemoteDescription(new RTCSessionDescription(data.offer))
                        .then(() => peerConnection.createAnswer())
                        .then(answer => peerConnection.setLocalDescription(answer))
                        .then(() => {
                            // 发送本地描述到远程端
                            WebsocketWebRtcUtil.send({'answer': peerConnection.localDescription});
                        });
                } else if (data.answer) {
                    // 收到远程应答
                    peerConnection.setRemoteDescription(new RTCSessionDescription(data.answer));
                } else if (data.iceCandidate) {
                    // 收到ICE candidate，添加到连接中
                    peerConnection.addIceCandidate(new RTCIceCandidate(data.iceCandidate));
                }
            }


            /*const message = JSON.parse(res.data);
            console.log(message)
            if (message.type === 'offer') {
                // 设置远程描述
                await peerConnection.setRemoteDescription(new RTCSessionDescription(message.offer));

                // 创建 Answer
                const answer = await peerConnection.createAnswer();
                await peerConnection.setLocalDescription(answer);

                // 发送 Answer 到信令服务器
                WebsocketWebRtcUtil.send(JSON.stringify({type: 'answer', answer: peerConnection.localDescription}));
            } else if (message.type === 'ice-candidate') {
                // 处理 ICE 候选项
                const candidate = new RTCIceCandidate(message.candidate);
                await peerConnection.addIceCandidate(candidate);
            }*/
        })

        if (socialuniSystemModule.isUniApp) {
            this.ws = uni.connectSocket({
                url: websocketUrl,
                complete: () => {
                },
            });
            //@ts-ignore
            this.ws.onClose(onClose);
            //@ts-ignore
            this.ws.onOpen(onOpen);
            //@ts-ignore
            this.ws.onError(onError);
            //@ts-ignore
            this.ws.onMessage(onMessage);
        } else {
            this.ws = new WebSocket(websocketUrl)

            this.ws.onopen = onOpen

            this.ws.onerror = onError

            this.ws.onclose = onClose

            this.ws.onmessage = onMessage
        }

    }

    static websocketClose() {
        if (this.ws && this.ws.readyState === this.ws.OPEN) {
            this.ws?.close(null);
        }
    }
}
