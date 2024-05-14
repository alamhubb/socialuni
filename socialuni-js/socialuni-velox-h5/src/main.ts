import {createApp} from 'vue'

import App from './App.vue'
import './styles/index.scss'
import Socialuni from "socialuni/src";
import router from "@/router";
import SocialuniMusic from "socialuni-music-sdk/src";
import DateUtil from "qing-util/src/util/DateUtil.ts";
import SocialuniUiH5 from "socialuni-ui-h5/src";
import SocialuniAppViewH5 from "socialuni-app-view-h5/src";
import SocialuniUser from "socialuni-user-sdk/src";
import SocialuniIm from "socialuni-im-sdk/src";
import SocialuniExpandH5 from "socialuni-expand-view-h5/src";
import SocialuniCommunityH5 from "socialuni-community-view-h5/src";

declare module '@vue/runtime-core' {
    interface ComponentCustomProperties {
        $DateUtil: typeof DateUtil
    }
}


// 使用自定义异步插件
(async () => {
    const app = createApp(App);

    app.config.globalProperties.$DateUtil = DateUtil;

    app.use(router)
    await Socialuni.install(app);
    await SocialuniMusic.install(app);
    app.use(SocialuniUiH5)
    app.use(SocialuniAppViewH5)
    app.use(SocialuniUser)
    app.mount('#app');
})();

