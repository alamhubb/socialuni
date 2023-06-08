import {SocialuniPlugin} from 'socialuni/src/interface/SocialuniPlugin'
import WebsocketUtil from "socialuni-api-base/src/websocket/WebsocketUtil";
import {socialuniConfigModule} from "./store/SocialuniConfigModule";

class SocialuniAppPlugin implements SocialuniPlugin {
    onLaunch() {
        WebsocketUtil.websocketConnect(false)
        socialuniConfigModule.getAppConfigAction()
    }

    onResponseErrorInterceptors(res){
        console.log(res)
    }
}


const socialuniAppPlugin: SocialuniPlugin = new SocialuniAppPlugin()

export default socialuniAppPlugin

//提供默认的
//可重写 可覆盖

//common
