import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import {App} from "vue";
import SocialuniIm from "socialuni-im-sdk/src";

const SocialuniImH5 = {
    install(app: App, expandView: Vue, socialuniOption?: SocialuniOption) {
        app.use(SocialuniIm)
        // socialuniPluginsModule.router.addRoute({
        //     path: '/message',
        //     name: 'message',
        //     component: SocialuniChatMessageViewH5,
        //     meta: {title: '消息'},
        // })
        // console.log(socialuniPluginsModule.router.getRoutes())
    }
}

export default SocialuniImH5
