<template>
    <div class="h100p">
        <div class="mx-sm pb-60">
            <el-button @click="joinClick">join</el-button>
            <el-button @click="leaveClick">leave</el-button>
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
    uid: 123456
}

@Component({
    components: {SDialog, Plus}
})
export default class HomePage extends Vue {
    $refs: {
        mapDialog: SDialog
    }

    localAudioTrack= null
    client= null

    created(){
        //实现语音通话逻辑
        //参考以下步骤实现语音通话的逻辑：
        // 调用 createClient 方法创建 AgoraRTCClient 对象。
        // 调用 join 方法加入一个 RTC 频道，你需要在该方法中传入 App ID 、用户 ID、Token、频道名称。
        // 先调用 createMicrophoneAudioTrack 通过麦克风采集的音频创建本地音频轨道对象；然后调用 publish 方法，将本地音频轨道对象当作参数即可将音频发布到频道中。
        // 当一个远端用户加入频道并发布音频轨道时：
        // 监听 client.on("user-published") 事件。当 SDK 触发该事件时，在这个事件回调函数的参数中你可以获取远端用户 AgoraRTCRemoteUser 对象 。
        // 调用 subscribe 方法订阅远端用户 AgoraRTCRemoteUser 对象，获取远端用户的远端音频轨道 RemoteAudioTrack 对象。
        // 调用 play 方法播放远端音频轨道。
        this.startBasicCall()
    }

    initAgora(){
        this.startBasicCall()
    }

    async startBasicCall() {
        // Create an AgoraRTCClient object.
        rtc.client = AgoraRTC.createClient({ mode: "rtc", codec: "vp8" });

        // Listen for the "user-published" event, from which you can get an AgoraRTCRemoteUser object.
        rtc.client.on("user-published", async (user, mediaType) => {
            // Subscribe to the remote user when the SDK triggers the "user-published" event
            await rtc.client.subscribe(user, mediaType);
            console.log("subscribe success");

            // If the remote user publishes an audio track.
            if (mediaType === "audio") {
                // Get the RemoteAudioTrack object in the AgoraRTCRemoteUser object.
                const remoteAudioTrack = user.audioTrack;
                // Play the remote audio track.
                remoteAudioTrack.play();
            }

            // Listen for the "user-unpublished" event
            rtc.client.on("user-unpublished", async (user) => {
                // Unsubscribe from the tracks of the remote user.
                await rtc.client.unsubscribe(user);
            });

        });
    }


    async joinClick(){
        // Join an RTC channel.
        await rtc.client.join(options.appId, options.channel, options.token, options.uid);
        // Create a local audio track from the audio sampled by a microphone.
        rtc.localAudioTrack = await AgoraRTC.createMicrophoneAudioTrack();
        // Publish the local audio tracks to the RTC channel.
        await rtc.client.publish([rtc.localAudioTrack]);

        console.log("publish success!");
    }

    async leaveClick(){
        // Destroy the local audio track.
        rtc.localAudioTrack.close();

        // Leave the channel.
        await rtc.client.leave();
    }
}
</script>
