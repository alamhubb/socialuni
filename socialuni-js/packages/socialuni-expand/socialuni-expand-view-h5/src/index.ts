import {SocialuniPlugin} from "socialuni/src/interface/SocialuniPlugin";
import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import {App} from "vue";
import SocialuniExpandViewH5 from "./views/SocialuniExpandViewH5.vue";

class SocialuniExpandPlugin implements SocialuniPlugin {

}

const socialuniExpandPlugin: SocialuniPlugin = new SocialuniExpandPlugin()


const SocialuniExpandH5 = {
    async install(app: App, socialuniOption: SocialuniOption) {
        console.log(66666)
        console.log(app)
        console.log(        socialuniPluginsModule.router.addRoute({
            path: '/expand',
            name: 'expand',
            component: () => SocialuniExpandViewH5,
            meta: {title: '扩列'},
        }))
        socialuniPluginsModule.router.getRoutes().unshift({
            path: '/expand',
            name: 'expand',
            component: () => SocialuniExpandViewH5,
            meta: {title: '扩列'},
        })
        console.log(socialuniPluginsModule.router.getRoutes())
        socialuniPluginsModule.addPlugin(socialuniExpandPlugin)
    }
}

export default SocialuniExpandH5
