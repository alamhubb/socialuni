import {initSocialuniUserStore} from "./src/store/store";
import SocialuniConfig from "socialuni-api/src/config/SocialuniConfig";

const SocialuniUser = {
    install(vue, options?: SocialuniConfig) {
        new SocialuniConfig(options)
        initSocialuniUserStore()
    }
}
export default SocialuniUser
