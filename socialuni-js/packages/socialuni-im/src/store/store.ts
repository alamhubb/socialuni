import SocialChatModule from "./SocialChatModule";

export let socialChatModule: SocialChatModule

export function initSocialuniChatStore() {
    socialChatModule = new SocialChatModule()
}


