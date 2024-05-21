import {createApp} from 'vue'

import App from './App.vue'
import Socialuni from "socialuni/src";
import router from "@/router/router.ts";
import SocialuniMusic from "socialuni-music-sdk/src";
import DateUtil from "qing-util/src/util/DateUtil.ts";
import SocialuniUser from "socialuni-user-sdk/src";
import SocialuniIm from "socialuni-im-sdk/src";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule.ts";
import SocialuniUiH5 from "socialuni-ui-h5/src";
import SocialuniExpandH5 from "socialuni-expand-view-h5/src";
import SocialuniCommunityH5 from "socialuni-community-view-h5/src";
import SocialuniAppViewH5 from "socialuni-app-view-h5/src";
import './styles/index.scss'


export const getImageUrl = (path: string): string => {
    return new URL(`./assets/${path}`, import.meta.url).href
}

declare module '@vue/runtime-core' {
    interface ComponentCustomProperties {
        $DateUtil: typeof DateUtil
        $getImageUrl: (path: string) => string
    }
}


// 使用自定义异步插件
(async () => {
    const app = createApp(App);
    app.use(router)
    app.use(Socialuni, router)

    app.config.globalProperties.$DateUtil = DateUtil;
    app.config.globalProperties.$getImageUrl = getImageUrl;

    app.use(SocialuniUiH5)
    // app.use(SocialuniAppViewH5)
    // app.use(SocialuniUser)


    app.config.globalProperties.$qing = socialuniSystemModule
    app.mount('#app');
})();

