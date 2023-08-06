import {SocialuniPlugin} from '@socialuni/socialuni/src/interface/SocialuniPlugin'
import {SocialuniOption} from "@socialuni/socialuni/src/interface/socialuniOption";
import {socialuniPluginsModule} from "@socialuni/socialuni/src/store/SocialuniPluginsModule";
import {App} from "vue";

class  SocialuniCommunityPlugin implements SocialuniPlugin  {

}

const socialuniCommunityPlugin: SocialuniPlugin = new SocialuniCommunityPlugin()

const SocialuniCommunity = {
    async install(app: App, socialuniOption: SocialuniOption) {
        socialuniPluginsModule.addPlugin(socialuniCommunityPlugin)
    }
}

export default SocialuniCommunity
