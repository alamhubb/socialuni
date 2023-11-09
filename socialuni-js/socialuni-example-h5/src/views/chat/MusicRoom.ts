import {reactive} from "vue";
import AgoraRTC, {IBufferSourceAudioTrack, IRemoteAudioTrack} from "agora-rtc-sdk-ng";
import musicRequest from "@/plugins/musicRequest";
import {Base64} from 'js-base64';

const client = AgoraRTC.createClient({
    mode: "live",
    codec: "vp8"
});

const options = {
    // Pass your App ID here.
    appId: "5e681410a7434ce9bba3e268226ce537",
    // Set the channel name.
    channel: "aa",
    // Pass your temp token here.
    token: "007eJxTYMgv2u0/ccF5bxWvnNKKlSdnVxwQXezfc9RBUvjEB8VPTy0VGExTzSwMTQwNEs1NjE2SUy2TkhKNU43MLIyMzJJTTY3Ne/p9UhsCGRlOJF9kYmSAQBCfiSExkYEBABSmHsY=",
    // Set the user ID.
    uid: 121212
}

console.log(879789789)
console.log(options.uid)

// 客户 ID
const customerKey = "1a97b2fe76664ef68bfddf7256cf91d3"
// 客户密钥
const customerSecret = "999c0689cc794128b450c1d702f0e2f3"
// 拼接客户 ID 和客户密钥
const plainCredential = customerKey + ":" + customerSecret
// 使用 base64 进行编码
let encodedCredential = Base64.encode(plainCredential)
// 创建 authorization header
let authorizationField = "Basic " + encodedCredential

await client.join(options.appId, options.channel, options.token, options.uid);

client.on("user-published", async (user, mediaType) => {
    console.log('触发了订阅')
    console.log(user)
    console.log(mediaType)
    await client.subscribe(user, mediaType);
    if (mediaType === 'audio') {
        mucisRoomStore.localAudioTrack = user.audioTrack
        user.audioTrack.play();
    }
});

export class MusicRoom {
    client = client
    localAudioTrack: IBufferSourceAudioTrack | IRemoteAudioTrack = null
    zego = {
        appId: 1241473881,
        key: '8bc8e23df6b06eaeef3530d4a0de1520',
        server: 'wss://webliveroom1241473881-api.imzego.com/ws',
    }

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
        mucisRoomStore.localAudioTrack.stop();
        // this.reqquetest()
        // Leave the channel.
        // await mucisRoomStore.client.leave();
    }

    async reqquetest(streamUrl) {
        musicRequest.post(`api/cn/v1/projects/${options.appId}/cloud-player/players`, {
            "player": {
                "streamUrl": streamUrl,
                "channelName": options.channel,
                "token": options.token,
                "uid": 0,
                "idleTimeout": 300,
            }
        }, {
            headers: {
                Authorization: authorizationField
            }
        })
    }

    async reqquetest111() {
        musicRequest.get(`api/dev/v1/channel/user/${options.appId}/${options.channel}`, {
            headers: {
                Authorization: authorizationField
            }
        })
       /* musicRequest.get(`api/v1/projects/${options.appId}/cloud-player/players`, {
            headers: {
                Authorization: authorizationField
            }
        })*/
    }
}

export const mucisRoomStore: MusicRoom = reactive(new MusicRoom())
