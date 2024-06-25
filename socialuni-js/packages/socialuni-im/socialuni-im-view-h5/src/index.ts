import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import {App} from "vue";
import SocialuniChatMessageViewH5 from "./views/SocialuniChatMessageViewH5.vue";

const SocialuniImH5 = {
    async install(app: App, expandView: Vue, socialuniOption?: SocialuniOption) {
        socialuniPluginsModule.router.addRoute({
            path: '/message',
            name: 'message',
            component: SocialuniChatMessageViewH5,
            meta: {title: '消息'},
        })
        console.log(socialuniPluginsModule.router.getRoutes())
    }
}

export default SocialuniImH5
