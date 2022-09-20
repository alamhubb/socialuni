import 'core-js'

import Vue from 'vue'
import App from './App.vue'
//@ts-ignore
import * as utils from 'socialuni/utils'
// main.js
import store from '@/store'
import uView from 'uview-ui'
import SocialMinxinVue from '@/socialuni/SocialMinxinVue.vue'

Vue.config.productionTip = false
Vue.use(uView)
// register global utility filters.
Object.keys(utils).forEach(key => {
  Vue.filter(key, utils[key])
})

// 全局混入social必要内容
// Vue.mixin(SocialMinxinVue)

new App({
  store
}).$mount()
