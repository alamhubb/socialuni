import NotifyVO from "socialuni-api-base/src/model/NotifyVO";
import NotifyType from "socialuni-constant/constant/NotifyType";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {App} from "vue";
import {SocialuniPlugin} from "socialuni/src/interface/SocialuniPlugin";
import socialuniMusicStore from "./store/SocialuniMusicStore";
import CommonEventUtil from "socialuni-native-util/src/util/CommonEventUtil";
import SocialuniImEventKey from "socialuni-im-api/src/constant/SocialuniMusicEventConst";
import MessageViewParams from "socialuni-im-sdk/src/model/MessageViewParams";
import AgoraRTC from "agora-rtc-sdk-ng";

const agoraEvents = ['channel-media-relay-event',
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

const client = AgoraRTC.createClient({
    mode: "live",
    codec: "vp8"
});

class SocialuniMusicPlugin implements SocialuniPlugin {
    async onLaunch() {
        console.log('触发了音乐的')
        //用户未登录的情况下，也是可以加入频道听歌的
        //做到不绑定手机号，也可以注册用户，发消息等等
        /*  console.log('触发了Im')
          if (socialuniTokenModule.token) {
              socialuniChatModule.queryMineImUserInfo()
          }*/
        console.log('执行订阅')
        CommonEventUtil.on(SocialuniImEventKey.socialuniImPageInit, async (params: MessageViewParams) => {
            /*await socialuniMusicStore.getMusicInitDataAction()
            client.leave()
            socialuniMusicStore.setChannelName(params.chatId)
            await socialuniMusicStore.getMusicTokenAction(params.chatId)
            console.log('加入频道')
            await client.join(socialuniMusicStore.appId, params.chatId, socialuniMusicStore.musicToken)

            for (const event of agoraEvents) {
                client.on(event, async (user, mediaType) => {
                    if (event === 'user-published') {
                        console.log(`触发了订阅:${event}`)
                        console.log(user)
                        console.log(mediaType)
                        await client.subscribe(user, mediaType);
                        if (mediaType === 'audio') {
                            socialuniMusicStore.setLocalAudioTrack(user.audioTrack)
                            user.audioTrack.play();
                        }
                    }
                });
            }*/
        })
    }
}


const socialuniMusicPlugin: SocialuniPlugin = new SocialuniMusicPlugin()


const SocialuniMusic = {
    async install(app: App) {
        console.log('执行了添加插件')
        socialuniPluginsModule.addPlugin(socialuniMusicPlugin)
    }
}

export default SocialuniMusic
