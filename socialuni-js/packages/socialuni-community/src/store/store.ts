/*
import SocialChatModule from "./SocialChatModule";
import SocialChatFriendModule from "./SocialChatFriendModule";
import SocialLocationModule from "./SocialLocationModule";
import SocialAppModule from "./SocialAppModule";
import SocialCircleModule from "./SocialCircleModule";
import SocialTalkModule from "./SocialTalkModule";
import SocialTagModule from "./SocialTagModule";
import SocialNotifyModule from "./SocialNotifyModule";
import SocialPlatformModule from "./SocialPlatformModule";
import socialuniSystemModule from "./socialuniSystemModule";
import socialuniUserModule from "./socialuniUserModule";
import socialuniConfigModule from "./socialuniConfigModule";
import SocialuniImUserAPI from "socialuni-base-api/src/api/SocialuniImUserAPI";
import UserService from "../service/UserService";
import CosAPI from "socialuni-base-api/src/api/CosAPI";

export let socialuniSystemModule: socialuniSystemModule
export let socialuniUserModule: socialuniUserModule
export let socialuniConfigModule: socialuniConfigModule
export let socialChatModule: SocialChatModule
export let socialChatFriendModule: SocialChatFriendModule
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
    socialuniSystemModule = new socialuniSystemModule()
    socialuniUserModule = new socialuniUserModule()
    socialuniConfigModule = new socialuniConfigModule()
    socialChatModule = new SocialChatModule()
    socialChatFriendModule = new SocialChatFriendModule()
    socialAppModule = new SocialAppModule()
    socialLocationModule = new SocialLocationModule()
    socialTalkModule = new SocialTalkModule()
    socialCircleModule = new SocialCircleModule()
    socialTagModule = new SocialTagModule()
    socialNotifyModule = new SocialNotifyModule()
    socialPlatformModule = new SocialPlatformModule()
    socialuniSystemModule.initSystemInfo()
    socialuniConfigModule.getAppConfigAction()
    socialuniConfigModule.getReportTypesAction()
    if (socialuniUserModule.token) {
        await socialuniUserModule.initSocialuniUserModule()
        UserService.getAppLunchDataByHasUser()
    }
    CosAPI.getCosPathAPI().then(res => {
        socialAppModule.cosHttpPath = res.data
    })
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


*/
