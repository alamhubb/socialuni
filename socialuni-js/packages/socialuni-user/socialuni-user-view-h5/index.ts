import {App, defineComponent} from "vue";
import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {socialuniIocContainerModule} from "socialuni/src/store/SocialuniIocContainerModule";
import SocialuniUserIocKey from "socialuni-user-sdk/src/constant/SocialuniUserIocKey";
import SocialuniUserRouterH5 from "./src/SocialuniUserRouterH5";


const SocialuniUserViewH5 = {
    async install(app: App, socialuniOption: SocialuniOption) {
        socialuniIocContainerModule.registry(SocialuniUserIocKey.socialuniUserDetailPageKey, SocialuniUserRouterH5)
    }
}

export default SocialuniUserViewH5
