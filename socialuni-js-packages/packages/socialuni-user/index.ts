import {initSocialuniUserStore} from "./src/store/store";
import SocialuniConfig from "socialuni-common/src/config/SocialuniConfig";

const SocialuniUser = {
    install(vue, options?: SocialuniConfig) {
        new SocialuniConfig(options)
        initSocialuniUserStore()
    }
}
export default SocialuniUser
