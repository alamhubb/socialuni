import {createApp} from 'vue'

import App from './App.vue'
import Socialuni from "socialuni/src";
import router from "@/router/router";

// 使用自定义异步插件
(async () => {
    const app = createApp(App);
    await Socialuni.install(app);
    app.use(router)
    app.mount('#app');
})();


