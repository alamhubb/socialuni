import SocialuniChatRO from "@socialuni/socialuni-api-base/src/model/SocialuniChatRO";
import SocialuniChatViewService from "@socialuni/socialuni-im-sdk/src/logic/SocialuniChatViewService";
import {socialuniMsgModule} from "@socialuni/socialuni-im-sdk/src/store/SocialMessageModule";

interface SocialuniChatViewServiceRefs {
    messageBox: HTMLDivElement;
}

export default class SocialuniChatViewServiceH5 extends SocialuniChatViewService {
    toMessagePage(chat: SocialuniChatRO) {
        console.log(chat)
        const chatId = super.toMessagePage(chat)
        console.log(78978)
        console.log(chatId)
        socialuniMsgModule.init({chatId: chatId})
        return chatId
    }
}
