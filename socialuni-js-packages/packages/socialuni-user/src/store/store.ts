import SocialUserModule from "./SocialUserModule";

export let socialUserModule: SocialUserModule

export function initSocialuniUserStore() {
    socialUserModule = new SocialUserModule()
}


