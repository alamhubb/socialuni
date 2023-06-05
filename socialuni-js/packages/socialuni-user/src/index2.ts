import {App} from "vue"
import Socialuni from "socialuni/src";

const SocialuniUser = {
    install(app: App) {
        app.use(Socialuni)

    }
}

export default SocialuniUser
