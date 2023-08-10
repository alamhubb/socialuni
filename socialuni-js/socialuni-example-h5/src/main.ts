import {createApp} from 'vue'

import { registerMicroApps, start } from 'qiankun';

import App from './App.vue'
import './styles/index.scss'
import Socialuni from "@socialuni/socialuni/src";
import router from "@/router";

registerMicroApps([
    {
        name: 'schedulerApp',
        entry: 'http://localhost:9532/',
        // entry: '/datascheduler/',
        container: '#microApp',
        activeRule: 'schedulerApp'
    }
]);

// 使用自定义异步插件
(async () => {
    const app = createApp(App);
    await Socialuni.install(app);
    app.use(router)
    app.mount('#app');

    start()
})();




