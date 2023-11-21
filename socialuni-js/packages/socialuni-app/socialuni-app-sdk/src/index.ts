import {SocialuniPlugin} from 'socialuni/src/interface/SocialuniPlugin'
import WebsocketUtil from "socialuni-api-base/src/websocket/WebsocketUtil";
import {socialuniConfigModule} from "./store/SocialuniConfigModule";
import {App, defineComponent, onErrorCaptured} from "vue";
import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import SocialuniAppAPI from "socialuni-app-api/src/api/SocialuniAppAPI";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import {InternalAxiosRequestConfig} from "axios/index";
import {socialAppModule} from "./store/SocialAppModule";
import SocialuniAppService from "./service/SocialuniAppService";

class SocialuniAppPlugin implements SocialuniPlugin {
    onLaunch() {
        // WebsocketUtil.websocketConnect(false)
        this.initApp()
    }

    async initApp() {
        try {
            // await socialuniConfigModule.getAppConfigAction()
            // socialAppModule.getHomeSwipersAction()
            // socialuniConfigModule.getReportTypesAction()
            SocialuniAppService.getAppLunchData()
        } catch (e) {

        }
    }

    onRequestInterceptors(config: InternalAxiosRequestConfig) {
        config.headers.provider = socialuniSystemModule.mpPlatform
        config.headers.mpPlatform = socialuniSystemModule.mpPlatform
        config.headers.platform = socialuniSystemModule.platform
        config.headers.system = socialuniSystemModule.system
    }

    onResponseErrorInterceptors(res) {
    }
}

const socialuniAppPlugin: SocialuniPlugin = new SocialuniAppPlugin()

const SocialuniApp = {
    async install(app: App, socialuniOption: SocialuniOption) {
        app.config.errorHandler = (e: Error, instance, info) => {
            console.error(e)
            // 处理错误，例如：报告给一个服务
            SocialuniAppAPI.sendErrorLogAPI('front page error', SocialuniAppUtil.RouterUtil.getCurrentPageURI(), e.message)
        }
        socialuniPluginsModule.addPlugin(socialuniAppPlugin)
    }
}

export default SocialuniApp
