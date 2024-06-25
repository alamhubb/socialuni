import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import QingScss from 'qing-scss/src/index'
import QingUiH5 from "qingjs-ui-h5/src";
import SocialuniUiH5 from "socialuni-ui-h5/src";
import Socialuni from "socialuni/src";
import SocialuniApp from "socialuni-app-sdk/src";
// import Socialuni from "socialuni/src";
const app = createApp(App)

app.use(router)
app.use(QingScss)
app.use(QingUiH5)
app.use(SocialuniUiH5)
app.use(SocialuniApp)
app.use(Socialuni)

app.mount('#app')
