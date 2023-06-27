import {socialuniChatModule} from "socialuni-im-sdk/src/store/SocialuniChatModule";
import SocialuniChatRO from "socialuni-api-base/src/model/SocialuniChatRO";
import DateUtil from "socialuni-util/src/util/DateUtil";
import {watch} from "vue";
import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";
import {Vue} from "vue-class-component";

interface SocialuniChatViewServiceRefs {
    messageBox: HTMLDivElement;
}

export default class SocialuniChatViewService extends SocialuniViewService<SocialuniChatViewServiceRefs> {

    initService(vueInstance: SocialuniChatViewServiceRefs) {
        super.initService(vueInstance)
        watch(() => socialuniChatModule.scrollTop, () => {
            console.log('触发了滚动')
            console.log(this.$refs)
            if (this.$refs.messageBox) {
                console.log(this.$refs.messageBox)
                this.$refs.messageBox.scrollTop = socialuniChatModule.scrollTop
            }
            // this.scrollTop = -1000
        })
    }

    scrollTop: number = 0

    formatTime(time: any) {
        return DateUtil.formatTime(time)
    }


    get chat(): SocialuniChatRO {
        if (socialuniChatModule.chat) {
            console.log(socialuniChatModule.chat.messages)
            console.log(socialuniChatModule.chat.messages.length)

            return socialuniChatModule.chat
        }
        return null
    }

    get chatList() {
        return socialuniChatModule.chats
    }


    setChatId(chat: SocialuniChatRO) {
        socialuniChatModule.setChatIdAndQueryMsg(chat.id)
    }
}
