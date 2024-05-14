import {SocialuniPlugin} from "socialuni/src/interface/SocialuniPlugin";
import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import {App} from "vue";
import {Vue} from "vue-facing-decorator";
import SocialuniTalkViewH5 from "./views/SocialuniTalkViewH5.vue";
import CommonUtil from "qing-util/src/util/CommonUtil";

class SocialuniCommunityH5Plugin implements SocialuniPlugin {

}

const socialuniCommunityH5Plugin: SocialuniPlugin = new SocialuniCommunityH5Plugin()


const SocialuniCommunityH5 = {
    async install(app: App, page = {path: '/', name: 'community', title: '社区'}) {
        console.log(66666)
        console.log(app)
        socialuniPluginsModule.router.addRoute({
            path: page.path,
            name: page.name1,
            component: SocialuniTalkViewH5,
            meta: {title: page.title},
        })
        console.log(socialuniPluginsModule.router.getRoutes())
        socialuniPluginsModule.addPlugin(socialuniCommunityH5Plugin)

        CommonUtil.delayTime(100).then(() => {
            console.log(socialuniPluginsModule.router.getRoutes().length)
            console.log(socialuniPluginsModule.router.getRoutes().map(item => item.path))
            console.log('chufale')
            console.log('333')
            // this.defaultActive = '/expand'
            console.log('zhixingle push')
            socialuniPluginsModule.router.push('/')
        })
    }
}

export default SocialuniCommunityH5
