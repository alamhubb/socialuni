import 'core-js'
import Vue from 'vue'
import App from './App.vue'
import store from './plugins/store'
import * as utils from '@/utils'
import MsgInput from '@/components/MsgInput.vue'
// main.js
import uView from 'uview-ui'

Vue.use(uView)

Vue.component('MsgInput', MsgInput)

Vue.config.productionTip = false

// register global utility filters.
Object.keys(utils).forEach(key => {
  Vue.filter(key, utils[key])
})

new App({
  store
}).$mount()
