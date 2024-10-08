import NotifyVO from "socialuni-api-base/src/model/NotifyVO";
import NotifyType from "socialuni-constant/constant/NotifyType";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {App} from "vue";
import {SocialuniPlugin} from "socialuni/src/interface/SocialuniPlugin";
import socialuniMusicStore from "./store/SocialuniMusicStore";
import CommonEventUtil from "qing-compat-js/src/util/CommonEventUtil";
import SocialuniImEventKey from "socialuni-im-api/src/constant/SocialuniMusicEventConst";
import MessageViewParams from "socialuni-im-sdk/src/model/MessageViewParams";
import SocialuniMusicRoleId from "./constant/SocialuniMusicRoleId";
import SocialuniMusicAPI from "./api/SocialuniMusicAPI";
import {SocialuniMusicRoomInfoRO} from "./model/SocialuniMusicRoomPlayerInfoRO";
import {socialuniTokenModule} from "socialuni-user-sdk/src/store/SocialuniTokenModule";
import {socialuniChatModule} from "socialuni-im-sdk/src/store/SocialuniChatModule";
import MusicPlayerSongPlayingInfoRO from "./model/MusicPlayerSongPlayingInfoRO";
import MusicPlayerSongInfoRO from "./model/MusicPlayerSongInfoRO";

class SocialuniMusicPlugin implements SocialuniPlugin {
    async onLaunch() {
        //用户未登录的情况下，也是可以加入频道听歌的
        //做到不绑定手机号，也可以注册用户，发消息等等
        /*  console.log('触发了Im')
          if (socialuniTokenModule.token) {
              socialuniChatModule.queryMineImUserInfo()
          }*/
        console.log('执行订阅')

        //页面初始化的时候，设置房间号
        CommonEventUtil.on(SocialuniImEventKey.socialuniImPageInit, async (params: MessageViewParams) => {
            socialuniMusicStore.setMusicRoomId(params.chatId)
            if (socialuniTokenModule.token) {
                const userRes = await SocialuniMusicAPI.queryMusicRoomUserInfoAPI(socialuniMusicStore.musicRoomId)
                socialuniMusicStore.setMusicRoleId(userRes.data.musicRoleId)
            }
            const res1 = await SocialuniMusicAPI.querySongListAPI(socialuniMusicStore.musicRoomId)
            const songList: MusicPlayerSongInfoRO[] = res1.data
            socialuniMusicStore.setSongList(songList)

            const res = await SocialuniMusicAPI.queryMusicRoomPlayerInfoAPI(socialuniMusicStore.musicRoomId)

            const roomInfo: MusicPlayerSongPlayingInfoRO = res.data

            //先在播放列表中，找到这个歌曲
            socialuniMusicStore.setMusicRoomInfo(roomInfo)

            /*await socialuniMusicStore.getMusicInitDataAction()
            client.leave()
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

    onMessage(notify: NotifyVO<MusicPlayerSongPlayingInfoRO>) {
        if (notify.type === NotifyType.music) {
            this.receiveMusicMsg(notify)
        }
    }


    private async receiveMusicMsg(notify: NotifyVO<MusicPlayerSongPlayingInfoRO>) {
        console.log('收到了消息：', notify.data)
        const msgSocialuniMusicRoomInfoRO = notify.data as MusicPlayerSongPlayingInfoRO


        // msgSocialuniMusicRoomInfoRO.musicRoleId = socialuniMusicStore.musicRoomInfo.musicRoleId
        await socialuniMusicStore.setMusicRoomInfo(msgSocialuniMusicRoomInfoRO)
        socialuniMusicStore.querySongList()
    }
}


const socialuniMusicPlugin: SocialuniPlugin = new SocialuniMusicPlugin()


const SocialuniMusic = {
    async install(app: App) {
        app.config.globalProperties.$socialuniMusicStore = socialuniMusicStore
        app.config.globalProperties.SocialuniMusicRoleId = SocialuniMusicRoleId

        socialuniPluginsModule.addPlugin(socialuniMusicPlugin)
    }
}

export default SocialuniMusic
