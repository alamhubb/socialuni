import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './plugins/router/router'
import '@/plugins/router/interceptor'
import * as filters from '@/plugins/filters/index'
import '@/styles/index.scss'
import './plugins/element'
import components from '@/components'
import appConfig from '@/plugins/appConfig'
import echarts from 'echarts'
import ColorUtil from '@/utils/ColorUtil'

import VueClipboard from 'vue-clipboard2'

Vue.use(components)
Vue.use(appConfig)
Vue.use(VueClipboard)

Vue.config.productionTip = false

/*echarts.registerTheme('default', {
    color: ColorUtil.echarts_colors
})

echarts.registerTheme('troopRotation', {
    color: ColorUtil.echarts_colors_rotate
})*/

Vue.prototype.$echarts = echarts


// register global utility filters.

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
