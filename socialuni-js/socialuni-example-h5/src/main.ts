import {createApp} from 'vue'

import App from './App.vue'
import './styles/index.scss'
import Socialuni from "@socialuni/socialuni/src";
import router from "@/router";
import SocialuniMusic from "@socialuni/socialuni-music-sdk/src";

// 使用自定义异步插件
(async () => {
    const app = createApp(App);
    await SocialuniMusic.install(app);
    await Socialuni.install(app);
    app.use(router)
    app.mount('#app');
})();
