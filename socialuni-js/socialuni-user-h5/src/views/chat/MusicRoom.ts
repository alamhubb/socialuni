import {reactive} from "vue";
import AgoraRTC from "agora-rtc-sdk-ng";
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
    channel: "51b26fe57a9d4d148d9b7df536eeebfa",
    // Pass your temp token here.
    token: "007eJxTYPgnM+sqs++9rVma4kWFYpGbT0oXSRaWLrl62WNxt1Ccj5ACg2mqmYWhiaFBormJsUlyqmVSUqJxqpGZhZGRWXKqqbF5RntoakMgI8Ntn99MjAwQCOIDdRomGZmlpZqaJ1qmmKQYmlikWCaZp6SZGpulpqYmpSUyMAAAe+slxg==",
    // Set the user ID.
    uid: Math.floor(Math.random() * 100000)
}

// await client.join(options.appId, options.channel, options.token, options.uid);

const events = ['channel-media-relay-event',
    'channel-media-relay-state',
    'connection-state-change',
    'content-inspect-connection-state-change',
    'content-inspect-error',
    'crypt-error',
    'exception',
    'image-moderation-connection-state-change',
    'is-using-cloud-proxy',
    'join-fallback-to-proxy',
    'live-streaming-error',
    'live-streaming-warning',
    'media-reconnect-end',
    'media-reconnect-start',
    'network-quality',
    'published-user-list',
    'stream-fallback',
    'stream-type-changed',
    'token-privilege-did-expire',
    'token-privilege-will-expire',
    'user-info-updated',
    'user-joined',
    'user-left',
    'user-published',
    'user-unpublished',
    'volume-indicator',]

for (const event of events) {
    client.on(event, async (user, mediaType) => {
        console.log(`触发了订阅:${event}`)
        if (event === 'user-published') {
            console.log(user)
            console.log(mediaType)
            await client.subscribe(user, mediaType);
            if (mediaType === 'audio') {
                // mucisRoomStore.localAudioTrack = user.audioTrack
                user.audioTrack.play();
            }
        }
    });
}

export class MusicRoom {
}

export const mucisRoomStore: MusicRoom = reactive(new MusicRoom())
