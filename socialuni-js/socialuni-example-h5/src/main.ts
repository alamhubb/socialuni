import {createApp} from 'vue'

import App from './App.vue'
import router from './router'
import './styles/index.scss'
import Socialuni from "@socialuni/socialuni/src";

const app = createApp(App)

app.use(router)
app.use(Socialuni, {router})

app.mount('#app')

