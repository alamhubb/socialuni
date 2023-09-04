import {createApp} from 'vue'

import App from './App.vue'
import './styles/index.scss'
import Socialuni from "@socialuni/socialuni/src";
import {createRouter, createWebHashHistory} from "vue-router";

// 使用自定义异步插件
(async () => {



    const app = createApp(App);
    await Socialuni.install(app);
    app.use(router)
    app.mount('#app');




})();
