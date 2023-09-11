import {createApp} from 'vue'

import App from './App.vue'
import Socialuni from "@socialuni/socialuni/src";
import router from "@/router/router";
import * as ElementPlusIconsVue from '@element-plus/icons-vue'


// 使用自定义异步插件
(async () => {

})();

const app = createApp(App);
await Socialuni.install(app);
app.use(router)
app.mount('#app');
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
