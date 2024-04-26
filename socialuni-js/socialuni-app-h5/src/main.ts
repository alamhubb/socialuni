import {createApp, defineComponent} from 'vue'

import App from './App.vue'
import './styles/index.scss'
import router from "@/router";
import DateUtil from "qing-util/src/util/DateUtil.ts";
import GlobalConst from "@/constant/GlobalConst.ts";
import QingUiH5 from "qing-ui-h5/src";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule.ts";
import Socialuni from "socialuni/src";

declare module '@vue/runtime-core' {
    interface ComponentCustomProperties {
        $DateUtil: typeof DateUtil
        $const: typeof GlobalConst
    }
}


// 使用自定义异步插件
(async () => {
    const app = createApp(App);

    app.config.globalProperties.$DateUtil = DateUtil;
    app.config.globalProperties.$const = GlobalConst;

    // app.use(QingUiH5)
    // await Socialuni.install(app);
    // for (const plugin of socialuniPluginsModule.plugins) {
    //     plugin && plugin.onLaunch && plugin.onLaunch()
    // }
    app.use(router)
    app.mount('#app');
})();

