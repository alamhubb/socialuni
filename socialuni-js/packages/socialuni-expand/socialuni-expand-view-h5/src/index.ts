import {SocialuniPlugin} from "socialuni/src/interface/SocialuniPlugin";
import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import {App} from "vue";
import {Vue} from "vue-facing-decorator";
import SocialuniExpandViewH5 from "./views/SocialuniExpandViewH5.vue";

class SocialuniExpandPlugin implements SocialuniPlugin {

}

const socialuniExpandPlugin: SocialuniPlugin = new SocialuniExpandPlugin()


socialuniPluginsModule.componentsMap.set('expandView', SocialuniExpandViewH5)


const SocialuniExpandH5 = {
    async install(app: App, expandView: Vue, socialuniOption?: SocialuniOption) {
        console.log(66666)
        console.log(app)
        socialuniPluginsModule.router.addRoute({
            path: '/expand',
            name: 'expand',
            component: () => {
                if (expandView) {
                    return expandView
                } else {
                    return SocialuniExpandViewH5
                }
            },
            meta: {title: '扩列'},
        })
        console.log(socialuniPluginsModule.router.getRoutes())
        socialuniPluginsModule.addPlugin(socialuniExpandPlugin)
    }
}

export default SocialuniExpandH5
