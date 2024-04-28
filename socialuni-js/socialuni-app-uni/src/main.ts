import {createSSRApp} from "vue";
import App from "./App.vue";
import Socialuni from "socialuni/src"
import '@/styles/index.scss'
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import SocialuniApp from "socialuni-app-sdk/src";
import SocialuniUiUni from "socialuni-ui-uni/src";
import SocialuniUser from "socialuni-user-sdk/src";
import SocialuniIm from "socialuni-im-sdk/src";

export function createApp() {
    const app = createSSRApp(App);

    app.use(SocialuniUiUni)
    app.use(SocialuniApp)
    app.use(SocialuniUser)
    app.use(SocialuniIm)
    app.use(Socialuni)

    app.config.globalProperties.$qing = socialuniSystemModule

    return {
        app,
    };
}
