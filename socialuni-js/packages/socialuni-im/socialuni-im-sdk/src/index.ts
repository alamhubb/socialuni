import {SocialuniPlugin} from 'socialuni/src/interface/SocialuniPlugin'
import NotifyVO from "@socialuni/socialuni-api-base/src/model/NotifyVO";
import NotifyType from "@socialuni/socialuni-constant/constant/NotifyType";
import {socialuniChatModule} from "./store/SocialuniChatModule";
import {socialuniTokenModule} from "@socialuni/socialuni-user-sdk/src/store/SocialuniTokenModule";
import {socialuniPluginsModule} from "@socialuni/socialuni/src/store/SocialuniPluginsModule";
import {SocialuniOption} from "@socialuni/socialuni/src/interface/socialuniOption";
import {App} from "vue";

class SocialuniImPlugin implements SocialuniPlugin {
    onLaunch() {
        console.log('触发了Im')
        if (socialuniTokenModule.token) {
            socialuniChatModule.queryMineImUserInfo()
        }
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
