import {createApp} from 'vue'
import ElementPlus from 'element-plus'
import App from './App.vue'
import router from './router'
import '@/styles/index.scss'
import 'uno.css'
import 'element-plus/dist/index.css'
import JsonViewer from "vue3-json-viewer";
import "vue3-json-viewer/dist/index.css";
import '@mdi/font/css/materialdesignicons.min.css';

const app = createApp(App)

app.use(router)
app.use(ElementPlus)
app.use(JsonViewer);

app.mount('#app')
