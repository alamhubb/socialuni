import {socialuniChatModule} from "../store/SocialuniChatModule";

export default class SocialuniMsgViewLogic {

    get chat() {
        return socialuniChatModule.chat
    }

    get pageTitle() {
        return this.chat.nickname
    }
}
