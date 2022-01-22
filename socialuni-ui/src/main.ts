import {createSSRApp} from 'vue'
import {createPinia} from 'pinia'

import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import '@/styles/qing-scss/index.scss'

const app = createSSRApp(App)

app.use(createPinia())
app.use(ElementPlus)

app.mount('#app')
