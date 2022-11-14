import SocialuniConfig from "socialuni-common/src/config/SocialuniConfig";
import SocialuniUser from "socialuni-user/index.ts";
import {initSocialuniChatStore} from "./src/store/store";


const SocialuniIM = {
    install(app, options?: SocialuniConfig) {
        app.use(SocialuniUser, options)
        initSocialuniChatStore()
    }
}

export default SocialuniIM
