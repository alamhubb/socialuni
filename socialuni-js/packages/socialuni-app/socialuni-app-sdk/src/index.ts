import {SocialuniPlugin} from 'socialuni/src/interface/SocialuniPlugin'
import WebsocketUtil from "socialuni-api-base/src/websocket/WebsocketUtil";
import {socialuniConfigModule} from "./store/SocialuniConfigModule";
import {App, defineComponent} from "vue";
import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";

class SocialuniAppPlugin implements SocialuniPlugin {
    onLaunch() {
        WebsocketUtil.websocketConnect(false)
        socialuniConfigModule.getAppConfigAction()
    }

    onResponseErrorInterceptors(res){
    }
}

const socialuniAppPlugin: SocialuniPlugin = new SocialuniAppPlugin()

const SocialuniApp = {
    async install(app: App, socialuniOption: SocialuniOption) {
        if (socialuniSystemModule.isUniApp) {
            import.meta.globEager('../../socialuni-app-view-uni/src/styles/index.scss')
        } else {
            import.meta.globEager('../../socialuni-app-view-h5/src/styles/index.scss')
        }
        socialuniPluginsModule.addPlugin(socialuniAppPlugin)
    }
}

export default SocialuniApp
