import {SocialuniPlugin} from 'socialuni/src/interface/SocialuniPlugin'
import NotifyVO from "socialuni-api-base/src/model/NotifyVO";
import NotifyType from "socialuni-constant/constant/NotifyType";

class SocialuniAppPlugin implements SocialuniPlugin {
    onLaunch() {
        console.log('触发了Im')
        socialuniChatModule.getChatsAction()
    }
}


const socialuniAppPlugin: SocialuniPlugin = new SocialuniAppPlugin()

export default socialuniAppPlugin

//提供默认的
//可重写 可覆盖

//common
