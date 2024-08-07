import Vue from 'vue'
import '@/components/YComponent'
import App from './App.vue'
import store from './store'
import router from './router/router'
import '@/router'
import '@/styles/index.scss'
import './plugins/element'
import components from '@/components'
import GlobalConst from '@/constant/GlobalConst'
import VueClipBoard from 'vue-clipboard2'

Vue.use(VueClipBoard)
Vue.prototype.$const = GlobalConst

Vue.use(components)

Vue.config.productionTip = false

// register global utility filters.

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
