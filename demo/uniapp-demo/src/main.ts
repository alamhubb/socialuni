import 'core-js'

console.log('zhixingle')
import Vue from 'vue'
import App from './App.vue'
import '@/plugins/socialuni'

console.log('zhixingle')

import store from './store'
import * as utils from '@/utils'
// main.js
import uView from 'uview-ui'

Vue.use(uView)

Vue.config.productionTip = false

// register global utility filters.
Object.keys(utils).forEach(key => {
  Vue.filter(key, utils[key])
})

new App({
  store
}).$mount()
