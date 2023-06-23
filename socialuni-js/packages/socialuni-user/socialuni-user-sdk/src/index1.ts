import NotifyVO from "socialuni-api-base/src/model/NotifyVO";
import SocialuniTokenUtil from "./util/SocialuniTokenUtil";
import {InternalAxiosRequestConfig} from "axios";
import {SocialuniPlugin} from "socialuni/src/interface/SocialuniPlugin";

const socialuniUserPlugin: SocialuniPlugin = {
    name: 'socialuniUserPlugin',
    onLaunch() {
        console.log('触发了Im')
    },
    onMessage(notify: NotifyVO) {
        console.log('接受了消息')
    },
    onWebsocketConnected(reload: boolean) {
    },
    onRequestInterceptors(config: InternalAxiosRequestConfig) {
        const token = SocialuniTokenUtil.get()
        if (token) {
            config.headers.token = token
        } else {
            config.headers.token = null
        }
    }
}

export default socialuniUserPlugin


