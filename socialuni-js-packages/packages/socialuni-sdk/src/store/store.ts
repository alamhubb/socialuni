import SocialLocationModule from "./SocialLocationModule";
import SocialAppModule from "./SocialAppModule";
import SocialUserModule from "./SocialUserModule";
import SocialCircleModule from "./SocialCircleModule";
import SocialTalkModule from "./SocialTalkModule";
import SocialConfigModule from "./SocialConfigModule";
import SocialTagModule from "./SocialTagModule";
import SocialNotifyModule from "./SocialNotifyModule";
import SocialPlatformModule from "./SocialPlatformModule";
import SocialChatModule from "./SocialChatModule";
import SocialSystemModule from "./SocialSystemModule";


export let socialSystemModule: SocialSystemModule
export let socialLocationModule: SocialLocationModule
export let socialAppModule: SocialAppModule
export let socialUserModule: SocialUserModule
export let socialCircleModule: SocialCircleModule
export let socialTalkModule: SocialTalkModule
export let socialConfigModule: SocialConfigModule
export let socialTagModule: SocialTagModule
export let socialNotifyModule: SocialNotifyModule
export let socialPlatformModule: SocialPlatformModule
export let socialChatModule: SocialChatModule
// export let socialRouterModule: SocialRouterModule

// export let socialOAuthModule: SocialOAuthModule
export function initSocialuniStore() {
    socialSystemModule = new SocialSystemModule()
    socialLocationModule = new SocialLocationModule()
    socialAppModule = new SocialAppModule()
    socialUserModule = new SocialUserModule()
    socialConfigModule = new SocialConfigModule()
    socialTalkModule = new SocialTalkModule()
    socialCircleModule = new SocialCircleModule()
    socialTagModule = new SocialTagModule()
    socialNotifyModule = new SocialNotifyModule()
    socialPlatformModule = new SocialPlatformModule()
    socialChatModule = new SocialChatModule()
    // socialOAuthModule = new SocialOAuthModule()
    // socialRouterModule = new SocialRouterModule()
}


