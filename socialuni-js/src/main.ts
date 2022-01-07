import 'core-js'

import Vue from 'vue'
import App from './App.vue'
import './plugins/socialuni'
//@ts-ignore
import * as utils from 'socialuni/utils'
// main.js
import store from '@/store'

Vue.config.productionTip = false

// register global utility filters.
Object.keys(utils).forEach(key => {
  Vue.filter(key, utils[key])
})

new App({
  store
}).$mount()
