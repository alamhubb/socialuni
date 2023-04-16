import {SocialuniPlugin} from 'socialuni/src/interface/SocialuniPlugin'
import {socialLocationModule} from "./store/SocialLocationModule";
import {socialTalkModule} from "./store/SocialTalkModule";
import {socialuniTagModule} from "./store/SocialTagModule";
import {socialCircleModule} from "./store/SocialCircleModule";
import NotifyVO from "socialuni-base-api/src/model/NotifyVO";
import NotifyType from "socialuni-constant/constant/NotifyType";
import {socialNotifyModule} from "./store/SocialNotifyModule";

const socialuniCommunityPlugin: SocialuniPlugin = {
    onLaunch() {
        console.log('触发了社区')
        socialLocationModule.getHotDistrictsAction()
        socialTalkModule.getTalkTabs()
        socialuniTagModule.getHotTagsAction()
        socialuniTagModule.getHotTagTypesAction()
        socialCircleModule.getHotCirclesAction()
        socialCircleModule.getHotCircleTypesAction()
    },
    onMessage(notify: NotifyVO) {
        // todo 直接将这个评论添加到talk中
        if (notify.type === NotifyType.comment) {
            socialNotifyModule.addUnreadNotifies(notify.user)
        }
    }
}

export default socialuniCommunityPlugin
