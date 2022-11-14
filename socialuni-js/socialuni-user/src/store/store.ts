import SocialUserModule from "./SocialUserModule";
import SocialSystemModule from "./SocialSystemModule";
import SocialConfigModule from "./SocialConfigModule";

export let socialUserModule: SocialUserModule
export let socialSystemModule: SocialSystemModule
export let socialConfigModule: SocialConfigModule

export function initSocialuniUserStore() {
    socialSystemModule = new SocialSystemModule()
    socialConfigModule = new SocialConfigModule()
    socialUserModule = new SocialUserModule()
}


