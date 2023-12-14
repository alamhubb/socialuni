import {createApp} from 'vue'

import App from './App.vue'
import './styles/index.scss'
import Socialuni from "socialuni/src";
import router from "@/router";
import SocialuniMusic from "socialuni-music-sdk/src";
import DateUtil from "socialuni-util/src/util/DateUtil.ts";

declare module '@vue/runtime-core' {
    interface ComponentCustomProperties {
        $DateUtil: typeof DateUtil
    }
}


// 使用自定义异步插件
(async () => {
    const app = createApp(App);

    app.config.globalProperties.$DateUtil = DateUtil;

    await SocialuniMusic.install(app);
    await Socialuni.install(app);
    app.use(router)
    app.mount('#app');
})();

