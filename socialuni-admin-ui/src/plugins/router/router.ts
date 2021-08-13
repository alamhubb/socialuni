import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'

Vue.use(VueRouter)

/* Layout */
import Layout from '@/components/layout/Layout.vue'

export const constantRoutes = [
  {
    path: '/404',
    component: () => import('@/views/404.vue'),
    hidden: true
  },
  {
    path: '/login',
    component: Layout,
    hidden: false,
    showFirstChild: true,
    redirect: '/login',
    children: [
      {
        path: '',
        name: 'strengthMonitoring',
        component: () => import('@/views/login/index.vue'),
        meta: { title: '登陆', icon: 'strengthMonitoring' }
      }]
  },
  {
    path: '/',
    component: Layout,
    redirect: '/',
    children: [
      {
        path: '',
        name: 'strengthMonitoring',
        component: () => import('@/views/home/Home.vue'),
        meta: { title: '首页', icon: 'strengthMonitoring' }
      }]
  },
  {
    path: 'https://shejiao.qingchiapp.com/',
    meta: { title: '文档' }
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: constantRoutes
})

router.beforeEach(async(route, from, next) => {
  // start progress bar
  console.log(route.path)
  next()
})

export default router
