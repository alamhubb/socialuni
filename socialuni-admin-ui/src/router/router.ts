import Vue from 'vue'
import VueRouter from 'vue-router'
/* Layout */
import Layout from '@/layout/Layout.vue'
import RouterName from '@/constants/RouterName'

Vue.use(VueRouter)

export const constantRoutes = [
  {
    path: '/404',
    component: () => import('@/views/404.vue'),
    hidden: true
  },
  {
    path: '/login',
    component: Layout,
    hidden: true,
    showFirstChild: true,
    redirect: '/login',
    children: [
      {
        path: '',
        name: 'strengthMonitoring',
        component: () => import('@/views/login/login.vue'),
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
        name: RouterName.devAccount,
        // redirect: '/review',
        component: () => import('@/views/dev/devAccount.vue'),
        // children: showRouterList,
        meta: { title: '开发者信息', icon: 'strengthMonitoring' }
      }]
  },
  {
    path: '/contentAudit',
    component: Layout,
    redirect: '/',
    children: [
      {
        path: '',
        name: RouterName.contentAudit,
        // redirect: '/review',
        component: () => import('@/views/contentAudit/contentAudit.vue'),
        // children: showRouterList,
        meta: { title: RouterName.contentAudit, icon: 'strengthMonitoring' }
      }]
  },
  /* {
    path: '/',
    component: Layout,
    redirect: '/',
    children: [
      {
        path: '/' + RouterName.auditHistory,
        name: RouterName.auditHistory,
        // redirect: '/review',
        component: () => import('@/views/audit/auditHistory.vue'),
        // children: showRouterList,
        meta: { title: '审核历史', icon: 'strengthMonitoring' }
      }]
  }, {
    path: '/',
    component: Layout,
    redirect: '/',
    children: [
      {
        path: '/keywordsManage',
        name: RouterName.keywordsManage,
        component: () => import('@/views/keywordsManage/keywordsManage.vue'),
        meta: { title: '关键词管理', icon: 'strengthMonitoring' }
      }]
  }, {
    path: '/',
    component: Layout,
    redirect: '/',
    children: [
      {
        path: '/test',
        name: '测试',
        component: () => import('@/views/testTemp/testTemp.vue'),
        meta: { title: '测试', icon: 'strengthMonitoring' }
      }]
  },*/
  /* {
    path: 'https://shejiao.qingchiapp.com/',
    meta: { title: '文档' }
  },*/
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
