import SocialLocationModule from "./SocialLocationModule";
import SocialAppModule from "./SocialAppModule";
import SocialCircleModule from "./SocialCircleModule";
import SocialTalkModule from "./SocialTalkModule";
import SocialTagModule from "./SocialTagModule";
import SocialNotifyModule from "./SocialNotifyModule";
import SocialPlatformModule from "./SocialPlatformModule";
import {initSocialuniUserStore, socialSystemModule, socialUserModule} from "socialuni-user/src/store/store";
import SocialuniImUserAPI from "socialuni-user/src/api/SocialuniImUserAPI";
import {initSocialuniChatStore, socialChatModule} from "socialuni-im/src/store/store";

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
    initSocialuniUserStore()
    initSocialuniChatStore()
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


