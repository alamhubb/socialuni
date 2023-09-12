<template>
    <div class="h100p">

        <div>
            <!--            上一首，，下一首-->

            <div v-for="music in musicList">
                <div class="row-col-center">
                    <div class="flex-none">
                        <img class="size40 bd-radius" src="./assets/1663045004074.jpg">
                    </div>
                    <div class="flex-1">
                        <div class="row-col-center">
                            <div>

                                {{ music.musicName }}
                            </div>
                            <div>

                                {{ music.musicAuthor }}
                            </div>
                        </div>

                        <div>
                            <el-slider v-model="value1"/>
                        </div>
                    </div>
                </div>
                <div @click="isPlaying=!isPlaying" class="use-click">
                    <VideoPause v-if="isPlaying" class="size20 use-click"></VideoPause>
                    <VideoPlay v-else class="size20 use-click"></VideoPlay>
                </div>
            </div>

            背景乐：歌名 图片，暂停/播放
        </div>

        <!--        <el-input v-model="uid">uid</el-input>-->
        {{ options.uid }}
        <div class="mx-sm pb-60">
            <el-button @click="joinClick">join</el-button>
            <el-button @click="leaveClick">leave</el-button>
            <el-button @click="publish">publish</el-button>
        </div>
    </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator'
import SDialog from "@socialuni/socialuni-ui-h5/src/components/SComponents/SDialog.vue";
import {Plus} from '@element-plus/icons-vue'
import SocialuniPeiwanAPI from "@socialuni/socialuni-peiwan-api/src/api/SocialuniPeiwanAPI";
import PeiwanRO from "@socialuni/socialuni-admin-api/src/model/peiwan/PeiwanRO";
import AgoraRTC from "agora-rtc-sdk-ng"
import test1 from "./assets/test1.mp3"
import SocialuniMusicAPI from "@/api/SocialuniMusicAPI";
import type SocialuniMusicInfoRO from "@/model/SocialuniMusicInfoRO";

let rtc = {
    localAudioTrack: null,
    client: null
};


let options = {
    // Pass your App ID here.
    appId: "5e681410a7434ce9bba3e268226ce537",
    // Set the channel name.
    channel: "aaa",
    // Pass your temp token here.
    token: "007eJxTYPDqT36nIBq1M2cWV7TQl/oK/rWeDK2yayf+PZqfwXrHl0mBwTTVzMLQxNAg0dzE2CQ51TIpKdE41cjMwsjILDnV1Nj8XvO/lIZARoa67VcZGRkgEMRnZkhMTGRgAACRXh3F",
    // Set the user ID.
    uid: 'web' + Math.floor(Math.random() * 2021428)
}

const streamIp = 'https://cdxapp-1257733245.file.myqcloud.com/opentest/M800000puzgO0yRX1o.mp3'

const client = AgoraRTC.createClient({
    mode: "rtc",
    codec: "vp8"
});

client.on("user-published", async (user, mediaType) => {
    console.log('触发了订阅')
    await client.subscribe(user, mediaType);
    if (mediaType === 'audio') {
        user.audioTrack.play();
    }
});

@Component({
    components: {SDialog, Plus}
})
export default class HomePage extends Vue {
    $refs: {
        mapDialog: SDialog
    }

    musicList: SocialuniMusicInfoRO [] = []

    isPlaying = false

    localAudioTrack = null
    client = null
    value1 = null
    options = options

    init() {
    }

    async queryMusicListAPI() {
        const res = await SocialuniMusicAPI.queryMusicListAPI()
        this.musicList = res.data
    }


    created() {
        this.queryMusicListAPI()
        //实现语音通话逻辑
        //参考以下步骤实现语音通话的逻辑：
        // 调用 createClient 方法创建 AgoraRTCClient 对象。
        // 调用 join 方法加入一个 RTC 频道，你需要在该方法中传入 App ID 、用户 ID、Token、频道名称。
        // 先调用 createMicrophoneAudioTrack 通过麦克风采集的音频创建本地音频轨道对象；然后调用 publish 方法，将本地音频轨道对象当作参数即可将音频发布到频道中。
        // 当一个远端用户加入频道并发布音频轨道时：
        // 监听 client.on("user-published") 事件。当 SDK 触发该事件时，在这个事件回调函数的参数中你可以获取远端用户 AgoraRTCRemoteUser 对象 。
        // 调用 subscribe 方法订阅远端用户 AgoraRTCRemoteUser 对象，获取远端用户的远端音频轨道 RemoteAudioTrack 对象。
        // 调用 play 方法播放远端音频轨道。
    }

    async joinClick() {
        // Join an RTC channel.
        await client.join(options.appId, options.channel, options.token, options.uid);
    }

    async publish() {
        const config = {
            source: streamIp
        }
        const track = await AgoraRTC.createBufferSourceAudioTrack(config);
        track.play();
        track.startProcessAudioBuffer();
        await client.publish(track);
    }

    async leaveClick() {
        // Destroy the local audio track.
        rtc.localAudioTrack.close();

        // Leave the channel.
        await client.leave();
    }
}
</script>
