import { createApp } from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import 'element-plus/dist/index.css'
import '@/assets/style/normalize.css'
import 'nprogress/nprogress.css'
import {install} from '@/utlis'
import Socialuni from "@socialuni/socialuni/src";
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

// 使用自定义异步插件
(async () => {

})();

const app = createApp(App);
await Socialuni.install(app);
app.use(store)
app.use(router)
app.use(install)
app.mount('#app');
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
