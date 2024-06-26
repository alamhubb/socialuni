import {createApp} from 'vue'
import ElementPlus from 'element-plus'
import App from './App.vue'
import router from './router'
import './styles/index.scss'
import 'element-plus/dist/index.css'
import JsonViewer from "vue3-json-viewer";
import "vue3-json-viewer/dist/index.css";
import '@mdi/font/css/materialdesignicons.min.css';
import 'github-markdown-css/github-markdown.css'
import Socialuni from "socialuni/src";
import DateUtil from "qing-util/src/util/DateUtil.ts";
import SocialuniUiH5 from "socialuni-ui-h5/src";
import SocialuniAppViewH5 from "socialuni-app-view-h5/src";
import SocialuniUser from "socialuni-user-sdk/src";
import SocialuniCommunityH5 from "socialuni-community-view-h5/src";

export const getImageUrl = (path: string): string => {
    return new URL(`./assets/${path}`, import.meta.url).href
}

(async () => {
    const app = createApp(App);
    app.use(router)
    app.use(Socialuni, router)

    app.config.globalProperties.$DateUtil = DateUtil;
    app.config.globalProperties.$getImageUrl = getImageUrl;

    app.use(SocialuniUiH5)
    app.use(SocialuniAppViewH5)
    app.use(SocialuniUser)
    app.use(SocialuniCommunityH5)

    app.use(JsonViewer);

    app.mount('#app')
})();

