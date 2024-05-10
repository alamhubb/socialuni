import {createApp} from 'vue'

import App from './App.vue'
import './styles/index.scss'
import Socialuni from "socialuni/src";
import router from "@/router";
import SocialuniMusic from "socialuni-music-sdk/src";
import DateUtil from "qing-util/src/util/DateUtil.ts";
import SocialuniApp from "socialuni-app-sdk/src";
import SocialuniUser from "socialuni-user-sdk/src";
import SocialuniIm from "socialuni-im-sdk/src";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule.ts";
import SocialuniUiH5 from "socialuni-ui-h5/src";
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
    app.use(router)
    app.use(Socialuni, router)

    app.config.globalProperties.$DateUtil = DateUtil;

    app.use(SocialuniUiH5)
    app.use(SocialuniApp)
    app.use(SocialuniUser)
    app.use(SocialuniIm)
    app.use(SocialuniMusic)
    app.use(SocialuniExpandH5)
    app.use(SocialuniCommunityH5)


    app.config.globalProperties.$qing = socialuniSystemModule
    app.mount('#app');
})();

