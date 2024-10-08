import NotifyVO from "socialuni-api-base/src/model/NotifyVO";
import NotifyType from "socialuni-constant/constant/NotifyType";
import {socialuniChatModule} from "./store/SocialuniChatModule";
import {socialuniTokenModule} from "socialuni-user-sdk/src/store/SocialuniTokenModule";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {App, watch} from "vue";
import {SocialuniPlugin} from "socialuni/src/interface/SocialuniPlugin";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import SocialuniUserEventOn from "socialuni-user-sdk/src/event/SocialuniUserEventOn";
import NotifyAPI from "socialuni-app-api/src/api/NotifyAPI";

class SocialuniImPlugin implements SocialuniPlugin {
    onLaunch() {
        console.log('触发了Im')
        /*if (socialuniTokenModule.token) {
            socialuniChatModule.queryMineImUserInfo().then(() => {
                socialuniChatModule.getChatsAction()
            })
        }

        watch(() => socialuniUserModule.mineUser, () => {
            socialuniChatModule.getChatsAction()
        })*/
    }

    onShow() {
        socialuniChatModule.computedChatsUnreadNumTotalAction()
    }

    onMessage(notify: NotifyVO) {
        console.log('接受了消息')
        console.log(notify)
        if (notify.type === NotifyType.message) {
            // 暂不支持圈子功能，推送的时候把所有未读都推送过来，还没做，匹配成功的话在talk和match页都显示匹配成功通知？，还有阅读消息后后台也要清0
            socialuniChatModule.pushChatAndMessagesAction(notify.data)
        } else if (notify.type === NotifyType.notify) {
            socialuniChatModule.unreadNotify.push(notify.data)
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
