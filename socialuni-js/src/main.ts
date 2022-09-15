import 'core-js'

import Vue from 'vue'
import App from './App.vue'
//@ts-ignore
import * as utils from 'socialuni/utils'
// main.js
import store from '@/store'
import uView from 'uview-ui'

Vue.config.productionTip = false
Vue.use(uView)
// register global utility filters.
Object.keys(utils).forEach(key => {
  Vue.filter(key, utils[key])
})

new App({
  store
}).$mount()
