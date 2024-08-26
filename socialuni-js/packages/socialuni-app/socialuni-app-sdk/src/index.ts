import {SocialuniPlugin} from 'socialuni/src/interface/SocialuniPlugin'
import WebsocketUtil from "socialuni-api-base/src/websocket/WebsocketUtil";
import {socialuniConfigModule} from "./store/SocialuniConfigModule";
import {App, defineComponent, onErrorCaptured} from "vue";
import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import SocialuniAppAPI from "socialuni-app-api/src/api/SocialuniAppAPI";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import {InternalAxiosRequestConfig} from "axios/index";
import NotifyVO from "socialuni-api-base/src/model/NotifyVO";
import NotifyType from "socialuni-constant/constant/NotifyType";
import {socialuniChatModule} from "socialuni-im-sdk/src/store/SocialuniChatModule";
import {socialAppModule} from "./store/SocialAppModule";

class SocialuniAppPlugin implements SocialuniPlugin {
    onLaunch() {
        socialuniConfigModule.getAppConfigAction()
        // socialAppModule.getHomeSwipersAction()
        // socialuniConfigModule.getReportTypesAction()
        // WebsocketUtil.createWebsocket()

        /*const websocketUrl = SocialuniAPIConfig.socialuniWebsocketUrl + '/webrtc?token=' + token

WebsocketWebRtcUtil.easyWebRTC = FastWebRTC.createClient({
    wsUrl: websocketUrl
})
// 设置远程视频流到video元素
WebsocketWebRtcUtil.easyWebRTC.ontrack((event) => {
    WebsocketWebRtcUtil.remoteVideo.srcObject = event.streams[0];
})*/


    }

    onMessage(notify: NotifyVO) {
        if (notify.type === NotifyType.userCount) {
            socialAppModule.onlineUsersCount = notify.data
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
            SocialuniAppAPI.sendErrorLogAPI('front page error', QingAppUtil.RouterUtil.getCurrentPageURI(), e.message)
        }
        socialuniPluginsModule.addPlugin(socialuniAppPlugin)
    }
}

export default SocialuniApp
