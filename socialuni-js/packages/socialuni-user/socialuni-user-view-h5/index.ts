import {App, defineComponent} from "vue";
import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {socialuniIocContainerModule} from "socialuni/src/store/SocialuniIocContainerModule";
import SocialuniUserIocKey from "socialuni-user-sdk/src/constant/SocialuniUserIocKey";
import socialuniUserRouterH5 from "./src/SocialuniUserRouterH5";


const SocialuniUserViewH5 = {
    async install(app: App, socialuniOption: SocialuniOption) {
        console.log('zhixingle')
        socialuniIocContainerModule.registry(SocialuniUserIocKey.socialuniUserDetailPageKey, socialuniUserRouterH5)
    }
}

export default SocialuniUserViewH5
