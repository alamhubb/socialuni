import {createApp} from 'vue'

import App from './App.vue'
import './styles/index.scss'
import Socialuni from "@socialuni/socialuni/src";

// 使用自定义异步插件
(async () => {
    const app = createApp(App);
    await Socialuni.install(app);
    app.mount('#app');
})();
