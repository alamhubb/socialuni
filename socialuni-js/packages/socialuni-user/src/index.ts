import {InternalAxiosRequestConfig} from "axios";
import {SocialuniPlugin} from "socialuni/src/interface/SocialuniPlugin";
import {socialuniConfigModule} from "socialuni-app/src/store/SocialuniConfigModule";
import UserService from "./logic/UserService";
import {socialuniUserModule} from "./store/SocialuniUserModule";
import {socialuniTokenModule} from "./store/SocialuniTokenModule";
import WebsocketUtil from "socialuni-api-base/src/websocket/WebsocketUtil";
import NotifyVO from "socialuni-api-base/src/model/NotifyVO";
import NotifyType from "socialuni-constant/constant/NotifyType";

class SocialuniUserPlugin implements SocialuniPlugin {
    onLaunch() {
        console.log('chufale')
        socialuniUserModule.initSocialuniUserModule()
        WebsocketUtil.websocketConnect(false)
        socialuniConfigModule.getAppConfigAction()
        socialuniConfigModule.getReportTypesAction()
        // CosAPI.getCosPathAPI().then(res => {
        //     socialAppModule.cosHttpPath = res.data
        // })
        // socialAppModule.getHomeSwipersAction()
    }
    onWebsocketInterceptors(config:any) {
        const token = socialuniTokenModule.token
        if (token) {
            config.token = token
        } else {
            config.token = null
        }
    }
    onRequestInterceptors(config: InternalAxiosRequestConfig) {
        const token = socialuniTokenModule.token
        if (token) {
            config.headers.token = token
        } else {
            config.headers.token = null
        }
    }
}


const socialuniUserPlugin: SocialuniPlugin = new SocialuniUserPlugin()

export default socialuniUserPlugin

//提供默认的
//可重写 可覆盖

//common
