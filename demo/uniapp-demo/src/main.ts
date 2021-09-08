import 'core-js'

import Vue from 'vue'
import App from './App.vue'
import './plugins/socialuni'

import * as utils from 'socialuni/utils'
// main.js
import uView from 'uview-ui'
import store from '@/store'

Vue.use(uView)

Vue.config.productionTip = false

// register global utility filters.
Object.keys(utils).forEach(key => {
  Vue.filter(key, utils[key])
})

new App({
  store
}).$mount()
