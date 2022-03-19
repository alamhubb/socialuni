import {createSSRApp} from 'vue'
import {createPinia} from 'pinia'

import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import '@/styles/qing-scss/index.scss'

const app = createSSRApp(App)
import './plugins/socialuni'
//@ts-ignore
import * as utils from 'socialuni/utils'
import socialuni from "@/socialuni";
// main.js

app.use(socialuni)
app.use(createPinia())
app.use(ElementPlus)

app.mount('#app')
