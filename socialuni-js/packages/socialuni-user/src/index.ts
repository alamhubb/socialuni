import NotifyVO from "socialuni-api-base/src/model/NotifyVO";
import SocialuniTokenUtil from "./util/SocialuniTokenUtil";
import {InternalAxiosRequestConfig} from "axios";
import {SocialuniPlugin} from "socialuni/src/interface/SocialuniPlugin";
import {socialuniConfigModule} from "socialuni-app/src/store/SocialuniConfigModule";
import UserService from "./logic/UserService";
import {socialuniUserModule} from "./store/SocialuniUserModule";
import {socialuniTokenModule} from "./store/SocialuniTokenModule";

const socialuniUserPlugin: SocialuniPlugin = {
    name: 'socialuniUserPlugin',
    onLaunch() {
        console.log('chufale')
        socialuniUserModule.initSocialuniUserModule()
        UserService.getAppLunchDataByHasUser()
        socialuniConfigModule.getAppConfigAction()
        socialuniConfigModule.getReportTypesAction()
        // CosAPI.getCosPathAPI().then(res => {
        //     socialAppModule.cosHttpPath = res.data
        // })
        // socialAppModule.getHomeSwipersAction()
    },
    onMessage(notify: NotifyVO) {
        console.log('接受了消息')
    },
    onWebsocketConnect(reload: boolean) {
    },
    onWebsocketInterceptors(config:any) {
        const token = socialuniTokenModule.token
        if (token) {
            config.token = token
        } else {
            config.token = null
        }
    },
    onRequestInterceptors(config: InternalAxiosRequestConfig) {
        const token = socialuniTokenModule.token
        if (token) {
            config.headers.token = token
        } else {
            config.headers.token = null
        }
    }
}

export default socialuniUserPlugin


