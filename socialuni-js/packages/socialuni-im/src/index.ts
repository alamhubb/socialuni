import {SocialuniPlugin} from 'socialuni/src/interface/SocialuniPlugin'
import NotifyVO from "socialuni-base-api/src/model/NotifyVO";
import NotifyType from "socialuni-constant/constant/NotifyType";
import {socialChatModule} from "./store/SocialChatModule";

const socialuniImPlugin: SocialuniPlugin = {
    onLaunch() {
        console.log('触发了Im')
        socialChatModule.getChatsAction()
    },
    onMessage(notify: NotifyVO) {
        if (notify.type === NotifyType.message) {
            console.log('接受了消息')
            console.log(notify.chat)
            // 暂不支持圈子功能，推送的时候把所有未读都推送过来，还没做，匹配成功的话在talk和match页都显示匹配成功通知？，还有阅读消息后后台也要清0
            socialChatModule.pushChatAndMessagesAction(notify.chat)
        }
    }
}

export default socialuniImPlugin
