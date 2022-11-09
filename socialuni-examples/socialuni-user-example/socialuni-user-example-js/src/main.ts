import {createSSRApp} from "vue";
import App from "./App.vue";
import {createPinia} from "pinia";
import './styles/index.scss'
import SocialuniUser from "socialuni-user/index.ts"

const pinia = createPinia()

export function createApp() {
    const app = createSSRApp(App);
    app.use(pinia)
    app.use(SocialuniUser)
    return {
        app,
    };
}

