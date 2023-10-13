<template>
    <div>
        <div>456</div>
        <div>456</div>
        <el-button @click="joinClick">加入</el-button>
        <el-button @click="leaveClick">离开</el-button>
        <el-button @click="publish">发送</el-button>
    </div>
  <!--轮播图-->
    <banner/>
  <!--每日歌曲推荐-->
    <songList/>
  <!--播客-->
  <!--        <radio/>-->
  <!--独家放送-->
  <!--        <unique/>-->
  <!--最新音乐-->
  <!--        <newMusic/>-->
  <!--推荐MV-->
  <!--        <recommendMV/>-->
</template>

<script lang="ts">
import {Component, Vue} from "vue-facing-decorator";
import {defineAsyncComponent, ref} from "vue";
import banner from './recommend/banner.vue'
import songList from './recommend/songList.vue'
import SDialog from "@socialuni/socialuni-ui-h5/src/components/SComponents/SDialog.vue";

const radio = defineAsyncComponent(() => import('./recommend/radio.vue'))
const unique = defineAsyncComponent(() => import('../children/unique.vue'))
const newMusic = defineAsyncComponent(() => import('../children/newMusic.vue'))
const recommendMV = defineAsyncComponent(() => import('../children/recommendMV.vue'))
import AgoraRTC from "agora-rtc-sdk-ng"

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
    token: "007eJxTYNj+8OP7X44R8Zx/O2v4vzybYqbxf+en/7bqJ1ZLdK7zNFuuwGCaamZhaGJokGhuYmySnGqZlJRonGpkZmFkZJacamps/u+6RmpDICND5tS3DIxQCOIzMyQmJjIwAAAcYCIB",
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
    components: {banner, songList}
})
export default class RecommendView extends Vue {
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

