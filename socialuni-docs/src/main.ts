import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import './style.css'
import 'uno.css'
import JsonViewer from "vue3-json-viewer";
import "vue3-json-viewer/dist/index.css";
import '@mdi/font/css/materialdesignicons.min.css';

const app = createApp(App)

app.use(router)
app.use(JsonViewer);

app.mount('#app')
