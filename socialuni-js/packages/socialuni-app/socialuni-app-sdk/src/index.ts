import {SocialuniPlugin} from 'socialuni/src/interface/SocialuniPlugin'
import WebsocketUtil from "socialuni-api-base/src/websocket/WebsocketUtil";
import {socialuniConfigModule} from "./store/SocialuniConfigModule";
import {App, defineComponent} from "vue";
import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import UniappAPI from "uniapp-api/src";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import PlatformModuleLoadUtil from "socialuni-native-util/src/util/PlatformModuleLoadUtil";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";

class SocialuniAppPlugin implements SocialuniPlugin {
    onLaunch() {
        WebsocketUtil.websocketConnect(false)
        socialuniConfigModule.getAppConfigAction()
    }

    onResponseErrorInterceptors(res){
    }
}

const socialuniAppPlugin: SocialuniPlugin = new SocialuniAppPlugin()


//提供默认的
//可重写 可覆盖

//common


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
