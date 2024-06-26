import {App} from "vue";
import {SocialuniPlugin} from "socialuni/src/interface/SocialuniPlugin";
import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import SocialuniAppUniUtil from "./utils/SocialuniAppUniUtil";
import SocialuniApp from "socialuni-app-sdk/src";

class SocialuniAppUniPlugin implements SocialuniPlugin {
    onLaunch() {
        SocialuniAppUniUtil.checkUpdate()
    }
}

const socialuniAppUniPlugin: SocialuniPlugin = new SocialuniAppUniPlugin()

const SocialuniAppUni = {
    async install(app: App, socialuniOption: SocialuniOption) {
        app.use(SocialuniApp)
        socialuniPluginsModule.addPlugin(socialuniAppUniPlugin)
    }
}

export default SocialuniAppUni
