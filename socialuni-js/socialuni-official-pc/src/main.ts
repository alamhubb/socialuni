import {createApp} from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import './styles/index.scss'
import Socialuni from "socialuni/src";

const app = createApp(App)

app.use(router)
app.use(ElementPlus)
app.use(Socialuni, {router})

app.mount('#app')





