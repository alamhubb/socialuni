import {SocialuniPlugin} from 'socialuni/src/interface/SocialuniPlugin'
import NotifyVO from "socialuni-api-base/src/model/NotifyVO";
import NotifyType from "socialuni-constant/constant/NotifyType";
import {socialChatModule} from "./store/SocialChatModule";
import {socialuniChatModule} from "./store/SocialuniChatModule";

class SocialuniImPlugin implements SocialuniPlugin {
    onLaunch() {
        console.log('触发了Im')
        socialuniChatModule.getChatsAction()
    }

    onMessage(notify: NotifyVO) {
        console.log('接受了消息')
        if (notify.type === NotifyType.message) {
            console.log('接受了消息')
            console.log(notify.chat.messages.length)
            // 暂不支持圈子功能，推送的时候把所有未读都推送过来，还没做，匹配成功的话在talk和match页都显示匹配成功通知？，还有阅读消息后后台也要清0
            socialChatModule.pushChatAndMessagesAction(notify.chat)
        }
    }

    onWebsocketConnect(reload: boolean) {
        socialChatModule.getChatsAction()
    }
}


const socialuniImPlugin: SocialuniPlugin = new SocialuniImPlugin()

export default socialuniImPlugin

//提供默认的
//可重写 可覆盖

//common
