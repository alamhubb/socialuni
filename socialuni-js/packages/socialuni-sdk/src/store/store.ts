import SocialChatModule from "./SocialChatModule";
import SocialLocationModule from "./SocialLocationModule";
import SocialAppModule from "./SocialAppModule";
import SocialCircleModule from "./SocialCircleModule";
import SocialTalkModule from "./SocialTalkModule";
import SocialTagModule from "./SocialTagModule";
import SocialNotifyModule from "./SocialNotifyModule";
import SocialPlatformModule from "./SocialPlatformModule";
import SocialSystemModule from "./SocialSystemModule";
import SocialUserModule from "./SocialUserModule";
import SocialConfigModule from "./SocialConfigModule";
import SocialuniImUserAPI from "socialuni-api/src/api/SocialuniImUserAPI";

export let socialSystemModule: SocialSystemModule
export let socialUserModule: SocialUserModule
export let socialConfigModule: SocialConfigModule
export let socialChatModule: SocialChatModule
export let socialAppModule: SocialAppModule
export let socialLocationModule: SocialLocationModule
export let socialCircleModule: SocialCircleModule
export let socialTalkModule: SocialTalkModule
export let socialTagModule: SocialTagModule
export let socialNotifyModule: SocialNotifyModule
export let socialPlatformModule: SocialPlatformModule
// export let socialRouterModule: SocialRouterModule

// export let socialOAuthModule: SocialOAuthModule
export async function initSocialuniStore() {
    socialSystemModule = new SocialSystemModule()
    socialUserModule = new SocialUserModule()
    socialConfigModule = new SocialConfigModule()
    socialChatModule = new SocialChatModule()
    socialAppModule = new SocialAppModule()
    socialLocationModule = new SocialLocationModule()
    socialTalkModule = new SocialTalkModule()
    socialCircleModule = new SocialCircleModule()
    socialTagModule = new SocialTagModule()
    socialNotifyModule = new SocialNotifyModule()
    socialPlatformModule = new SocialPlatformModule()
    socialSystemModule.initSystemInfo()
    console.log(socialUserModule)
    await socialUserModule.initSocialuniUserModule()
    if (socialUserModule.user) {
        const imRes = await SocialuniImUserAPI.getImUserTokenAPI()
        socialUserModule.setImToken(imRes.data)
        socialChatModule.initSocialuniChatModule()
    }
    socialAppModule.getHomeSwipersAction()
    socialLocationModule.getHotDistrictsAction()
    socialTalkModule.getTalkTabs()
    socialTagModule.getHotTagsAction()
    socialTagModule.getHotTagTypesAction()
    socialCircleModule.getHotCirclesAction()
    socialCircleModule.getHotCircleTypesAction()
    // socialOAuthModule = new SocialOAuthModule()
    // socialRouterModule = new SocialRouterModule()
}


