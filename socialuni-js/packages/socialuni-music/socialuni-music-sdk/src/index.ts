import NotifyVO from "@socialuni/socialuni-api-base/src/model/NotifyVO";
import NotifyType from "@socialuni/socialuni-constant/constant/NotifyType";
import {socialuniChatModule} from "./store/SocialuniChatModule";
import {socialuniTokenModule} from "@socialuni/socialuni-user-sdk/src/store/SocialuniTokenModule";
import {socialuniPluginsModule} from "@socialuni/socialuni/src/store/SocialuniPluginsModule";
import {SocialuniOption} from "@socialuni/socialuni/src/interface/socialuniOption";
import {App} from "vue";
import {SocialuniPlugin} from "@socialuni/socialuni/src/interface/SocialuniPlugin";
import socialuniMusicStore from "./store/SocialuniMusicStore";
import CommonEventUtil from "@socialuni/socialuni-native-util/src/util/CommonEventUtil";
import SocialuniImEventKey from "@socialuni/socialuni-im-api/src/constant/SocialuniMusicEventConst";
import MessageViewParams from "@socialuni/socialuni-im-sdk/src/model/MessageViewParams";

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


class SocialuniImPlugin implements SocialuniPlugin {
    async onLaunch() {
        //用户未登录的情况下，也是可以加入频道听歌的
        //做到不绑定手机号，也可以注册用户，发消息等等
        await socialuniMusicStore.getMusicInitDataAction()
        /*  console.log('触发了Im')
          if (socialuniTokenModule.token) {
              socialuniChatModule.queryMineImUserInfo()
          }*/
        CommonEventUtil.on(SocialuniImEventKey.socialuniImPageInit, async (params: MessageViewParams) => {
            socialuniMusicStore.client.leave()
            await socialuniMusicStore.getMusicTokenAction()
            await socialuniMusicStore.client.join(socialuniMusicStore.appId, params.chatId, socialuniMusicStore.musicToken)

            for (const event of agoraEvents) {
                socialuniMusicStore.client.on(event, async (user, mediaType) => {
                    console.log(`触发了订阅:${event}`)
                    if (event === 'user-published') {
                        console.log(user)
                        console.log(mediaType)
                        await socialuniMusicStore.client.subscribe(user, mediaType);
                        if (mediaType === 'audio') {
                            socialuniMusicStore.localAudioTrack = user.audioTrack
                            user.audioTrack.play();
                        }
                    }
                });
            }
        })
    }

    onMessage(notify: NotifyVO) {
        console.log('接受了消息')
        if (notify.type === NotifyType.message) {
            console.log('接受了消息')
            console.log(notify.chat.messages.length)
            // 暂不支持圈子功能，推送的时候把所有未读都推送过来，还没做，匹配成功的话在talk和match页都显示匹配成功通知？，还有阅读消息后后台也要清0
            socialuniChatModule.pushChatAndMessagesAction(notify.chat)
        }
    }

    onWebsocketConnected(reload: boolean) {
        socialuniChatModule.getChatsAction()
    }
}


const socialuniImPlugin: SocialuniPlugin = new SocialuniImPlugin()


const SocialuniIm = {
    async install(app: App, socialuniOption: SocialuniOption) {
        socialuniPluginsModule.addPlugin(socialuniImPlugin)
    }
}

export default SocialuniIm
