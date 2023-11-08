import {reactive} from "vue";
import AgoraRTC, {IBufferSourceAudioTrack} from "agora-rtc-sdk-ng";

const client = AgoraRTC.createClient({
    mode: "rtc",
    codec: "vp8"
});

const options = {
    // Pass your App ID here.
    appId: "5e681410a7434ce9bba3e268226ce537",
    // Set the channel name.
    channel: "aa",
    // Pass your temp token here.
    token: "007eJxTYPg04SHv+r0PSzvfiPaLb2Je73Vt8uQkzk2B0Zv4Lfj747kUGExTzSwMTQwNEs1NjE2SUy2TkhKNU43MLIyMzJJTTY3NhSd6pzYEMjI0fdnDxMgAgSA+E0NiIgMDANwdHWo=",
    // Set the user ID.
    uid: 'web' + Math.floor(Math.random() * 2021428)
}

await client.join(options.appId, options.channel, options.token, options.uid);

client.on("user-published", async (user, mediaType) => {
    console.log('触发了订阅')
    await client.subscribe(user, mediaType);
    if (mediaType === 'audio') {
        user.audioTrack.play();
    }
});

export class MusicRoom {
    client = client
    localAudioTrack: IBufferSourceAudioTrack = null

    async publish(songId) {
        const config = {
            // source: `https://cdxapp-1257733245.file.myqcloud.com/opentest/M800000puzgO0yRX1o.mp3`
            source: `music/song/media/outer/url?id=${songId}.mp3`
        }
        mucisRoomStore.localAudioTrack = await AgoraRTC.createBufferSourceAudioTrack(config);
        mucisRoomStore.localAudioTrack.play();
        mucisRoomStore.localAudioTrack.startProcessAudioBuffer({
            startPlayTime: 100
        });
        await mucisRoomStore.client.publish(mucisRoomStore.localAudioTrack);
    }

    /*async joinClick() {

        // Join an RTC channel.
        await mucisRoomStore.client.join(options.appId, options.channel, options.token, options.uid);
    }*/

    async leaveClick() {
        // Destroy the local audio track.
        mucisRoomStore.localAudioTrack.close();

        // Leave the channel.
        // await mucisRoomStore.client.leave();
    }
}

export const mucisRoomStore: MusicRoom = reactive(new MusicRoom())
