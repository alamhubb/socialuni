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
import SocialuniChatViewH5 from "@socialuni/socialuni-im-view-h5/src/views/SocialuniChatViewH5.vue"
import SocialuniMsgViewH5 from "@socialuni/socialuni-im-view-h5/src/views/SocialuniMsgViewH5.vue"
import {VideoCamera} from "@element-plus/icons-vue";
import WebsocketWebRtcUtil from "@socialuni/socialuni-api-base/src/websocket/WebsocketWebRtcUtil";

let localStream
let localVideo = null
let remoteVideo = null
let peerConnection = null
@Component({
  components: {VideoCamera, SocialuniChatViewH5, SocialuniMsgViewH5}
})
export default class MessageView extends Vue {

  created() {
    console.log(11111111)
    this.initPeerConnection()
  }

  mounted() {
    localVideo = document.getElementById('localVideo');
    remoteVideo = document.getElementById('remoteVideo');

  }

// 初始化WebRTC连接
  async initPeerConnection() {
    const configuration = {iceServers: [{urls: 'stun:stun.l.google.com:19302'}]};
    WebsocketWebRtcUtil.peerConnection = new RTCPeerConnection(configuration);

    // 设置远程视频流到video元素
    WebsocketWebRtcUtil.peerConnection.ontrack = (event) => {
      remoteVideo.srcObject = event.streams[0];
    };

    // 处理ICE候选项
    WebsocketWebRtcUtil.peerConnection.onicecandidate = (event) => {
      if (event.candidate) {
        // 发送ICE候选项到远程端
        // 你可以使用WebSocket或其他通信方式将候选项发送给另一个客户端
      }
    };
  }


// 获取本地媒体流
  async start() {
    try {
      localStream = await navigator.mediaDevices.getUserMedia({video: false, audio: true});
      console.log(localVideo)
      localVideo.srcObject = localStream;
      // 添加本地音频和视频流到连接
      localStream.getTracks().forEach(track => {
        WebsocketWebRtcUtil.peerConnection.addTrack(track, localStream);
      });

    } catch (error) {
      console.error('Error accessing local media:', error);
    }
  }

  stop() {
    localStream.getTracks().forEach(track => {
      track.stop();
    });
    localVideo.srcObject = null;
    remoteVideo.srcObject = null;
    peerConnection.close();
  }
}
</script>
