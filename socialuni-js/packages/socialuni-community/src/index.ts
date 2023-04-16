import {SocialuniPlugin} from 'socialuni/src/interface/SocialuniPlugin'
import {socialLocationModule} from "./store/SocialLocationModule";
import {socialTalkModule} from "./store/SocialTalkModule";
import {socialuniTagModule} from "./store/SocialTagModule";
import {socialCircleModule} from "./store/SocialCircleModule";

const socialuniCommunityPlugin: SocialuniPlugin = {
    onLaunch() {
        console.log('触发了社区')
        socialLocationModule.getHotDistrictsAction()
        socialTalkModule.getTalkTabs()
        socialuniTagModule.getHotTagsAction()
        socialuniTagModule.getHotTagTypesAction()
        socialCircleModule.getHotCirclesAction()
        socialCircleModule.getHotCircleTypesAction()
    }
}

export default socialuniCommunityPlugin
