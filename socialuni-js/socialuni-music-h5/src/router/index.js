import { createRouter, createWebHashHistory } from 'vue-router'
import {routes} from "@/router/path.js";
import store from '@/store'
import eventBus from '@/utlis/eventbus.js'



const router = createRouter({
  history: createWebHashHistory(),
  routes,
  scrollBehavior: () => ({ left: 0, top: 0 })
})


router.beforeEach(to => {
  // if (!store.state.login.profile && to.path !== '/findMusic'){
  //   eventBus.emit('login')
  //   return '/findMusic'
  // }
})

export default router
