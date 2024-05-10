import {SocialuniPlugin} from "socialuni/src/interface/SocialuniPlugin";
import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import {App} from "vue";
import {Vue} from "vue-facing-decorator";
import SocialuniTalkViewH5 from "./views/SocialuniTalkViewH5.vue";

class SocialuniCommunityH5Plugin implements SocialuniPlugin {

}

const socialuniCommunityH5Plugin: SocialuniPlugin = new SocialuniCommunityH5Plugin()


const SocialuniCommunityH5 = {
    async install(app: App, view: Vue, socialuniOption?: SocialuniOption) {
        console.log(66666)
        console.log(app)
        socialuniPluginsModule.router.addRoute({
            path: '/community',
            name: 'community',
            component: () => {
                if (view) {
                    return view
                } else {
                    return SocialuniTalkViewH5
                }
            },
            meta: {title: '社区'},
        })
        console.log(socialuniPluginsModule.router.getRoutes())
        socialuniPluginsModule.addPlugin(socialuniCommunityH5Plugin)
    }
}

export default SocialuniCommunityH5
