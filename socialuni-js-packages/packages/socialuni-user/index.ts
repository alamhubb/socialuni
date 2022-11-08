import {initSocialuniUserStore} from "./src/store/store";
import "socialuni-scss/src/styles/index.scss"

const SocialuniUser = {
    install() {
        initSocialuniUserStore()
    }
}
export default SocialuniUser
