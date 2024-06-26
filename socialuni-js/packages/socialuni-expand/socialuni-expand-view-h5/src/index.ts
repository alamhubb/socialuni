import {SocialuniPlugin} from "socialuni/src/interface/SocialuniPlugin";
import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import {App} from "vue";
import {Vue} from "vue-facing-decorator";
import SocialuniExpandViewH5 from "./views/SocialuniExpandViewH5.vue";
import SocialuniIm from "socialuni-im-sdk/src";

class SocialuniExpandPluginH5 implements SocialuniPlugin {

}

const socialuniExpandPlugin: SocialuniPlugin = new SocialuniExpandPluginH5()


socialuniPluginsModule.componentsMap.set('expandView', SocialuniExpandViewH5)


const SocialuniExpandH5 = {
    install(app: App, expandView: Vue, socialuniOption?: SocialuniOption) {
        // app.use(SocialuniIm)
        socialuniPluginsModule.router.addRoute({
            path: '/expand',
            name: 'expand',
            component: SocialuniExpandViewH5,
            meta: {title: '扩列'},
        })
        console.log(socialuniPluginsModule.router.getRoutes())
        socialuniPluginsModule.addPlugin(socialuniExpandPlugin)
    }
}

export default SocialuniExpandH5
