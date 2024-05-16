import {SocialuniPlugin} from 'socialuni/src/interface/SocialuniPlugin'
import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import {App} from "vue";
import SocialuniUserLikeAPI from "socialuni-expand-api/src/api/SocialuniUserLikeAPI";
import {socialuniLikeConfigModule} from "./store/SocialuniLikeConfigModule";

class SocialuniExpandPlugin implements SocialuniPlugin {
    onLaunch() {
        SocialuniUserLikeAPI.getAllConfig().then(res => {
            socialuniLikeConfigModule.setConfig(res.data)
        })
    }
}

const socialuniExpandPlugin: SocialuniPlugin = new SocialuniExpandPlugin()

const SocialuniExpand = {
    async install(app: App, socialuniOption: SocialuniOption) {
        socialuniPluginsModule.addPlugin(socialuniExpandPlugin)
    }
}

export default SocialuniExpand
