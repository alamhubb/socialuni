import {createSSRApp} from "vue";
import App from "./App.vue";
import Socialuni from "socialuni/src/index.ts"
import '@/styles/index.scss'

export function createApp() {
    const app = createSSRApp(App);
    app.use(Socialuni,'asdf')
    return {
        app,
    };
}

