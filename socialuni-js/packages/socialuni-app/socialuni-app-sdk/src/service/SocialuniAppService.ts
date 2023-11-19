import WebsocketUtil from "@socialuni/socialuni-api-base/src/websocket/WebsocketUtil";
import WebsocketWebRtcUtil from "@socialuni/socialuni-api-base/src/websocket/WebsocketWebRtcUtil";
import JsonUtil from "@socialuni/socialuni-util/src/util/JsonUtil";
import test1 from './test1.mp3'

export default class SocialuniAppService {
    static async getAppLunchData() {
        /* && !socialChatModule.imToken   // 修复imToken过期后，需要重新登录的才能聊天的bug。 https://gitee.com/socialuni/socialuni/issues/I6GGP7
        * */
        // const imRes = await SocialuniImUserAPI.getImUserTokenAPI()
        // socialChatModule.setImToken(imRes.data)
        WebsocketUtil.websocketConnect(false)
        WebsocketWebRtcUtil.websocketConnect(false)

        console.log(787987)
        console.log(test1)
        console.log(665656)
        console.log(789879)
        console.log(665656)

        const audio = new Audio('./test1.mp3')

        console.log(audio)
        console.log(audio.play())
        console.log(665656)


        const startCallButton = document.getElementById('startCall');
        let localStream;
        let peerConnection;
        const configuration = {'iceServers': [{'urls': 'stun:stun.l.google.com:19302'}]};

        startCallButton.addEventListener('click', () => {
            navigator.mediaDevices.getUserMedia({audio: true, video: false})
                .then(stream => {
                    localStream = stream;
                    peerConnection = new RTCPeerConnection(configuration);

                    // Add stream to the connection
                    localStream.getTracks().forEach(track => {
                        peerConnection.addTrack(track, localStream);
                    });

                    // Setup ICE handling
                    peerConnection.onicecandidate = event => {
                        if (event.candidate) {
                            sendMessage({type: 'candidate', candidate: event.candidate});
                        }
                    };

                    // Create an offer
                    peerConnection.createOffer()
                        .then(offer => peerConnection.setLocalDescription(offer))
                        .then(() => {
                            sendMessage({type: 'offer', offer: peerConnection.localDescription});
                        });
                })
                .catch(error => console.error('Error accessing media devices:', error));
        });

        function handleSignalingData(data) {
            switch (data.type) {
                case 'offer':
                    createAnswer(data.offer);
                    break;
                case 'answer':
                    peerConnection.setRemoteDescription(new RTCSessionDescription(data.answer));
                    break;
                case 'candidate':
                    peerConnection.addIceCandidate(new RTCIceCandidate(data.candidate));
                    break;
            }
        }

        function createAnswer(offer) {
            peerConnection.setRemoteDescription(new RTCSessionDescription(offer));
            peerConnection.createAnswer()
                .then(answer => peerConnection.setLocalDescription(answer))
                .then(() => {
                    sendMessage({type: 'answer', answer: peerConnection.localDescription});
                });
        }

        // Implement sendMessage function to send signaling data to the server
        function sendMessage(message) {
            // 发送消息到服务器的代码
            WebsocketWebRtcUtil.send(message)
        }

        // TODO: Implement WebSocket or other technology to receive messages from server
        // and call handleSignalingData when a message is received
        // socialChatModule.initSocialuniChatModule()
        /*socialNotifyModule.queryNotifiesAction()
        */
        // socialChatModule.initSocialuniChatModule()
    }
}
