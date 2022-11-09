import SocialUserModule from "./SocialUserModule";
import SocialSystemModule from "./SocialSystemModule";

export let socialUserModule: SocialUserModule
export let socialSystemModule: SocialSystemModule

export function initSocialuniUserStore() {
    socialSystemModule = new SocialSystemModule()
    socialUserModule = new SocialUserModule()
}


