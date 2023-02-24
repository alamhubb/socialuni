import {createSSRApp} from "vue";
import App from "./App.vue";
import {createPinia} from "pinia";
import Socialuni from "socialuni-sdk/index.ts"
import '@/styles/index.scss'

const pinia = createPinia()

export function createApp() {
    const app = createSSRApp(App);
    app.use(pinia)
    console.log(1123)
    app.use(Socialuni)
    return {
        app,
    };
}

