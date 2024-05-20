import {createSSRApp} from "vue";
import App from "./App.vue";
import Socialuni from "socialuni/src"
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import SocialuniUiUni from "socialuni-ui-uni/src";
import SocialuniUser from "socialuni-user-sdk/src";
import SocialuniIm from "socialuni-im-sdk/src";
import SocialuniExpand from "socialuni-expand-sdk/src";
import SocialuniAppUni from "socialuni-app-view-uni/src";
import '@/styles/index.scss'

export function createApp() {
    const app = createSSRApp(App);

    console.log('zhixingle createApp')
    app.use(SocialuniUiUni)
    app.use(SocialuniAppUni)
    app.use(SocialuniUser)
    app.use(SocialuniIm)
    app.use(SocialuniExpand)
    app.use(Socialuni)

    app.config.globalProperties.$qing = socialuniSystemModule

    return {
        app,
    };
}

