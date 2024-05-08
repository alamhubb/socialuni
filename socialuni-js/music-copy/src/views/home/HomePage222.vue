<template>
    <div class="h100p">
        <div class="mx-sm pb-60">
            <el-button @click="joinClick">join</el-button>
            <el-button @click="leaveClick">leave</el-button>
        </div>
        <div>
            <el-button @click="loading">测试</el-button>
        </div>
    </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import QDialog from "qing-ui-h5/src/components/QDialog.vue";
import {Plus} from '@element-plus/icons-vue'
import SocialuniPeiwanAPI from "socialuni-peiwan-api/src/api/SocialuniPeiwanAPI";
import PeiwanRO from "socialuni-admin-api/src/model/peiwan/PeiwanRO";
import AgoraRTC from "agora-rtc-sdk-ng"
import openRequest from "socialuni-api-base/src/OpenRequest";

let rtc = {
    localAudioTrack: null,
    client: null
};
import heroicAdventureMp3 from "./assets/HeroicAdventure.mp3"

const appId = '5e681410a7434ce9bba3e268226ce537'
const streamIp = 'https://cdxapp-1257733245.file.myqcloud.com/opentest/M800000puzgO0yRX1o.mp3'

const userId = '1a97b2fe76664ef68bfddf7256cf91d3'
const key = '1a97b2fe76664ef68bfddf7256cf91d3'
const secret = '999c0689cc794128b450c1d702f0e2f3'

const uidHeader = 'qqq'
const tokenHeader = '007eJxTYDi+2Ftdds/mXxN/+iw6/7xHWSjI6YDypDMbBSd0H7p/zr9FgcE01czC0MTQINHcxNgkOdUyKSnRONXIzMLIyCw51dTY/EXXv5SGQEYGm9KbTIwMTAyMQAjiMzMUFhYCAHfPIUU='

let options = {
    // Pass your App ID here.
    appId: appId,
    // Set the channel name.
    channel: "aaa",
    // Pass your temp token here.
    token: "007eJxTYPDqT36nIBq1M2cWV7TQl/oK/rWeDK2yayf+PZqfwXrHl0mBwTTVzMLQxNAg0dzE2CQ51TIpKdE41cjMwsjILDnV1Nj8XvO/lIZARoa67VcZGRkgEMRnZkhMTGRgAACRXh3F",
    // Set the user ID.
    uid: 123456
}

const client = AgoraRTC.createClient({
    mode: "rtc",
    codec: "vp8"
});

@toNative
@Component({
    components: {SDialog, Plus}
})
export default class HomePage extends Vue {
    $refs: {
        mapDialog: SDialog
    }


    localAudioTrack = null
    client = null

    //https://cdxapp-1257733245.file.myqcloud.com/opentest/M800000puzgO0yRX1o.mp3

    created() {
        //实现语音通话逻辑
        //参考以下步骤实现语音通话的逻辑：
        // 调用 createClient 方法创建 AgoraRTCClient 对象。
        // 调用 join 方法加入一个 RTC 频道，你需要在该方法中传入 App ID 、用户 ID、Token、频道名称。
        // 先调用 createMicrophoneAudioTrack 通过麦克风采集的音频创建本地音频轨道对象；然后调用 publish 方法，将本地音频轨道对象当作参数即可将音频发布到频道中。
        // 当一个远端用户加入频道并发布音频轨道时：
        // 监听 client.on("user-published") 事件。当 SDK 触发该事件时，在这个事件回调函数的参数中你可以获取远端用户 AgoraRTCRemoteUser 对象 。
        // 调用 subscribe 方法订阅远端用户 AgoraRTCRemoteUser 对象，获取远端用户的远端音频轨道 RemoteAudioTrack 对象。
        // 调用 play 方法播放远端音频轨道。
        this.initAgora()
    }

    audioMixing = {
        state: "IDLE",
        // "IDLE" | "LOADING | "PLAYING" | "PAUSE"
        duration: 0
    }

    audioMixingTrack = null

    async loading(file) {
        console.log('触发了')
        if (this.audioMixing.state === "PLAYING" || this.audioMixing.state === "LOADING") return;
        const options = {};
        if (file) {
            options.source = file;
        } else {
            options.source = heroicAdventureMp3
        }
        try {
            console.log(111111)
            this.audioMixing = {...this.audioMixing, state: "LOADING"}
            if (this.audioMixingTrack) {
                await client.unpublish(this.audioMixingTrack);
            }
            console.log(2222)
            // start audio mixing with local file or the preset file
            const track = await AgoraRTC.createBufferSourceAudioTrack(options);
            this.audioMixingTrack = track;
            track.play();
            console.log(3333)
            track.startProcessAudioBuffer({
                loop: true
            });
            this.audioMixing = {
                ...this.audioMixing,
                state: "PLAYING",
                duration: track.duration
            }
            console.log(4444)
        } catch (e) {
            console.error(e);
            // message.error(e.message)
            this.audioMixing = {...this.audioMixing, state: "IDLE"}
        }
    }

    initAgora() {
       /* openRequest.get(`/api/cn/v1/projects/${appId}/cloud-player/players`, {
            params: {
                'player': {
                    'uid': 101,
                    'channelName': 'class1',
                    'streamUrl': 'https://cdxapp-1257733245.file.myqcloud.com/opentest/M800000puzgO0yRX1o.mp3'
                }
            }
        })*/
    }
}
</script>
