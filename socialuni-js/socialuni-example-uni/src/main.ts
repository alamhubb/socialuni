import {createSSRApp} from "vue";
import App from "./App.vue";
import Socialuni from "socialuni/src"
import '@/styles/index.scss'
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";

export function createApp() {
    const app = createSSRApp(App);
    app.use(Socialuni)

    app.config.globalProperties.$qing = socialuniSystemModule

    return {
        app,
    };
}

