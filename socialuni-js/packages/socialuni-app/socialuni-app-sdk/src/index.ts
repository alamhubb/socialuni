import {SocialuniPlugin} from 'socialuni/src/interface/SocialuniPlugin'
import WebsocketUtil from "socialuni-api-base/src/websocket/WebsocketUtil";
import {socialuniConfigModule} from "./store/SocialuniConfigModule";
import {App, defineComponent, onErrorCaptured} from "vue";
import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import SocialuniAppAPI from "socialuni-app-api/src/api/SocialuniAppAPI";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";

class SocialuniAppPlugin implements SocialuniPlugin {
    onLaunch() {
        WebsocketUtil.websocketConnect(false)
        socialuniConfigModule.getAppConfigAction()
    }

    onResponseErrorInterceptors(res) {
    }
}

const socialuniAppPlugin: SocialuniPlugin = new SocialuniAppPlugin()

const SocialuniApp = {
    async install(app: App, socialuniOption: SocialuniOption) {
        app.config.errorHandler = (e: Error, instance, info) => {
            // 处理错误，例如：报告给一个服务
            SocialuniAppAPI.sendErrorLogAPI('front page error', SocialuniAppUtil.RouterUtil.getCurrentPageURI(), e.message)
        }
        if (socialuniSystemModule.isUniApp) {
            import.meta.globEager('../../socialuni-app-view-uni/src/styles/index.scss')
        } else {
            import.meta.globEager('../../socialuni-app-view-h5/src/styles/index.scss')
        }
        socialuniPluginsModule.addPlugin(socialuniAppPlugin)
    }
}

export default SocialuniApp
