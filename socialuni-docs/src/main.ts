import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import '@/styles/index.scss'
import JsonViewer from "vue3-json-viewer";
import "vue3-json-viewer/dist/index.css";
import '@mdi/font/css/materialdesignicons.min.css';
import "tailwindcss/tailwind.css"

const app = createApp(App)

app.use(router)
app.use(JsonViewer);

app.mount('#app')
