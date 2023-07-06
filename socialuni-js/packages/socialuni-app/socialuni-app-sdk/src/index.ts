import {SocialuniPlugin} from 'socialuni/src/interface/SocialuniPlugin'
import WebsocketUtil from "@socialuni/socialuni-api-base/src/websocket/WebsocketUtil";
import {socialuniConfigModule} from "./store/SocialuniConfigModule";
import {App, defineComponent, onErrorCaptured} from "vue";
import {SocialuniOption} from "@socialuni/socialuni/src/interface/socialuniOption";
import {socialuniSystemModule} from "@socialuni/socialuni-util/src/store/SocialuniSystemModule";
import {socialuniPluginsModule} from "@socialuni/socialuni/src/store/SocialuniPluginsModule";
import SocialuniAppAPI from "@socialuni/socialuni-app-api/src/api/SocialuniAppAPI";
import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";
import {InternalAxiosRequestConfig} from "axios/index";
import {socialuniTokenModule} from "@socialuni/socialuni-user-sdk/src/store/SocialuniTokenModule";

class SocialuniAppPlugin implements SocialuniPlugin {
    onLaunch() {
        WebsocketUtil.websocketConnect(false)
        socialuniConfigModule.getAppConfigAction()
    }

    onRequestInterceptors(config: InternalAxiosRequestConfig) {
        console.log(socialuniPluginsModule.uid)
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
        console.log('执行了安装')
        app.config.errorHandler = (e: Error, instance, info) => {
            // 处理错误，例如：报告给一个服务
            SocialuniAppAPI.sendErrorLogAPI('front page error', SocialuniAppUtil.RouterUtil.getCurrentPageURI(), e.message)
        }
        console.log(socialuniPluginsModule.uid)
        console.log(socialuniPluginsModule.plugins.length)
        console.log(33333333)
        socialuniPluginsModule.addPlugin(socialuniAppPlugin)
        console.log(socialuniPluginsModule.plugins.length)
    }
}

export default SocialuniApp
