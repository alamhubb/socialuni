<template>
    <div class="flex-row h100p overflow-hidden">
        <div>
            <audio id="localVideo" autoplay muted controls></audio>
            <audio id="remoteVideo" autoplay controls></audio>
            <div>
                <el-button @click="start">播放</el-button>
                <el-button @click="stop">Stop</el-button>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator';
import SocialuniChatViewH5 from "socialuni-im-view-h5/src/views/SocialuniChatViewH5.vue"
import SocialuniMsgViewH5 from "socialuni-im-view-h5/src/views/SocialuniMsgViewH5.vue"
import {VideoCamera} from "@element-plus/icons-vue";
import WebsocketWebRtcUtil from "socialuni-api-base/src/websocket/WebsocketWebRtcUtil";
import test1 from './test1.mp3'

let localStream
let localVideo = null
@Component({
    components: {VideoCamera, SocialuniChatViewH5, SocialuniMsgViewH5}
})
export default class MessageView extends Vue {
    created() {
        console.log(11111111)
    }

    mounted() {
        localVideo = document.getElementById('localVideo');
        WebsocketWebRtcUtil.remoteVideo = document.getElementById('remoteVideo');
    }


// 获取本地媒体流
    async start() {
        const audio = new Audio(test1);
        audio.oncanplaythrough = (() => {
                localStream = audio.captureStream();
                WebsocketWebRtcUtil.socialuniWebRTC.pushStream(localStream)
            }
        )
        audio.play()
        /*try {
            localStream = await navigator.mediaDevices.getUserMedia({video: false, audio: true});
            console.log(localVideo)
            localVideo.srcObject = localStream;
            localStream.getTracks().forEach(track => {
                WebsocketWebRtcUtil.peerConnection.addTrack(track, localStream);
            });

            WebsocketWebRtcUtil.peerConnection.createOffer()
                .then(offer => WebsocketWebRtcUtil.peerConnection.setLocalDescription(offer))
                .then(() => {
                    // 发送本地描述到远程端
                    WebsocketWebRtcUtil.send({ 'offer': WebsocketWebRtcUtil.peerConnection.localDescription });
                });
        } catch (error) {
            console.error('Error accessing local media:', error);
        }*/
    }

    stop() {
        localStream.getTracks().forEach(track => {
            track.stop();
        });
        localVideo.srcObject = null;
        WebsocketWebRtcUtil.remoteVideo.srcObject = null;
        WebsocketWebRtcUtil.peerConnection.close();
    }
}
</script>
