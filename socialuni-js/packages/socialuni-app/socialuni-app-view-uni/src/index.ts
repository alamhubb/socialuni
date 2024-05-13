import SocialuniAppUniUtil from "./utils/SocialuniAppUniUtil";
import SocialuniApp from "socialuni-app-sdk/src";
import {App} from "vue";

const SocialuniAppViewUni = {
    async install(app: App) {
        SocialuniAppUniUtil.checkUpdate()
        app.use(SocialuniApp)
    }
}

export default SocialuniAppViewUni
