import {createSSRApp} from "vue";
import App from "./App.vue";
import {createPinia} from "pinia";
import './styles/index.scss'
import SocialuniIM from "socialuni-im/index.ts"

const pinia = createPinia()

export function createApp() {
    const app = createSSRApp(App);
    app.use(pinia)
    app.use(SocialuniIM)
    return {
        app,
    };
}

