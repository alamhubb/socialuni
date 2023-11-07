import {reactive} from "vue";
import AgoraRTC from "agora-rtc-sdk-ng";


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

export class MusicRoom {
    client = client

}

export const mucisRoomStore: MusicRoom = reactive(new MusicRoom())
