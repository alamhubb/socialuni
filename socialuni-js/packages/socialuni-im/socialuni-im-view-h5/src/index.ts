import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import {App} from "vue";
import {Vue} from "vue-facing-decorator";
import SocialuniChatViewH5 from "./views/SocialuniChatViewH5.vue";

const SocialuniImH5 = {
    async install(app: App, expandView: Vue, socialuniOption?: SocialuniOption) {
        console.log(66666)
        console.log(app)
        socialuniPluginsModule.router.addRoute({
            path: '/chat',
            name: 'chat',
            component: SocialuniChatViewH5,
            meta: {title: '消息'},
        })
        console.log(socialuniPluginsModule.router.getRoutes())
    }
}

export default SocialuniImH5
