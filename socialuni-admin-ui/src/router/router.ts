import Vue from 'vue'
import VueRouter from 'vue-router'
/* Layout */
import Layout from '@/layout/Layout.vue'
import RouterName from '@/constants/RouterName'

Vue.use(VueRouter)

export const menuRoutes = [
  {
    path: '',
    name: RouterName.devAccount,
    component: () => import('@/views/devInfo/devInfo.vue'),
    meta: { title: '开发者信息', icon: 'strengthMonitoring' }
  },
  {
    path: 'contentAudit',
    name: RouterName.contentAudit,
    component: () => import('@/views/audit/contentAudit.vue'),
    // children: showRouterList,
    meta: { title: RouterName.contentAudit, icon: 'strengthMonitoring' }
  },
  {
    path: '/keywordManage',
    name: RouterName.keywordsManage,
    component: () => import('@/views/keywordManage/keywordManage.vue'),
    meta: { title: RouterName.keywordsManage, icon: 'strengthMonitoring' }
  },
  {
    path: '/imgAuditHistory',
    name: RouterName.imgAuditHistory,
    component: () => import('@/views/audit/imgAuditHistory.vue'),
    meta: { title: RouterName.imgAuditHistory, icon: 'strengthMonitoring' }
  }
]

export const constantRoutes = [
  {
    path: '/404',
    component: () => import('@/views/404.vue'),
    hidden: true
  },
  {
    path: '/login',
    component: () => import('@/views/login/login.vue'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/',
    children: menuRoutes
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
  },{
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
    path: 'https://socialuni.cn/',
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
