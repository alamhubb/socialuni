import {createSSRApp} from "vue";
import App from "./App.vue";
import {createPinia} from "pinia";
import Socialuni from "socialuni-sdk/index.ts"
import SocialuniIM from "socialuni-im/index.ts"
import SocialuniUser from "socialuni-user/index.ts"

const pinia = createPinia()

export function createApp() {
    const app = createSSRApp(App);
    app.use(pinia)
    app.use(SocialuniUser)
    app.use(SocialuniIM)
    app.use(Socialuni)
    return {
        app,
    };
}

