import SocialuniChatRO from "socialuni-api-base/src/model/SocialuniChatRO";
import SocialuniChatViewService from "socialuni-im-sdk/src/logic/SocialuniChatViewService";
import {socialuniMsgModule} from "socialuni-im-sdk/src/store/SocialMessageModule";

interface SocialuniChatViewServiceRefs {
    messageBox: HTMLDivElement;
}

export default class SocialuniChatViewServiceH5 extends SocialuniChatViewService {
    toMessagePage(chat: SocialuniChatRO) {
        const chatId = super.toMessagePage(chat)
        socialuniMsgModule.init({chatId: chatId})
        return chatId
    }
}
