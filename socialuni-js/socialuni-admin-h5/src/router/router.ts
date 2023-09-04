import { createRouter, createWebHistory } from 'vue-router'
/* Layout */
import Layout from '@/layout/Layout.vue'
import RouterName from '@/constant/RouterName'
import TokenUtil from '@/utils/TokenUtil'
import {socialuniUserModule} from "@socialuni/socialuni-user-sdk/src/store/SocialuniUserModule";


export const menuRoutes = [
  /*{
    path: '',
    name: RouterName.devAccount,
    component: () => import('@/views/devInfo/devInfo.vue'),
    meta: { title: '开发者信息', icon: 'strengthMonitoring', isOpen: true }
  },
  {
    path: 'contentAudit',
    name: RouterName.contentAudit,
    component: () => import('@/views/audit/contentAudit.vue'),
    // children: showRouterList,
    meta: { title: RouterName.contentAudit, icon: 'strengthMonitoring', isOpen: true }
  },
  {
    path: '/keywordManage',
    name: RouterName.keywordsManage,
    component: () => import('@/views/keywordManage/keywordManage.vue'),
    meta: { title: RouterName.keywordsManage, icon: 'strengthMonitoring' }
  },
  {
    path: '/configManage',
    name: RouterName.configManage,
    component: () => import('@/views/configManage/configManage.vue'),
    meta: { title: RouterName.configManage, icon: 'strengthMonitoring', isOpen: true }
  },
  {
    path: '/homeSwiperManage',
    name: RouterName.homeSwiperManage,
    component: () => import('@/views/homeSwiperManage/homeSwiperManage.vue'),
    meta: { title: RouterName.homeSwiperManage, icon: 'strengthMonitoring', isOpen: true }
  },
  {
    path: '/stickTalkManage',
    name: RouterName.stickTalkManage,
    component: () => import('@/views/stickTalkManage/stickTalkManage.vue'),
    meta: { title: RouterName.stickTalkManage, icon: 'strengthMonitoring', isOpen: true }
  },
  {
    path: '/imgAuditHistory',
    name: RouterName.imgAuditHistory,
    component: () => import('@/views/audit/imgAuditHistory.vue'),
    meta: { title: RouterName.imgAuditHistory, icon: 'strengthMonitoring' }
  },
  {
    path: '/userIdentityAudit',
    name: RouterName.userIdentityAudit,
    component: () => import('@/views/userIdentityAudit/UserIdentityAuditView.vue'),
    meta: { title: RouterName.userIdentityAudit, icon: 'strengthMonitoring' }
  },*/
  {
    path: '/peiwanManage',
    name: 'peiwanManage',
    component: () => import('@/views/peiwanManage/PeiwanManageView.vue'),
    meta: { title: '陪玩管理', icon: 'strengthMonitoring' }
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
        component: () => import('@/views/testTemp/UserIdentityAuditView.vue'),
        meta: { title: '测试', icon: 'strengthMonitoring' }
      }]
  },*/
  /* {
    path: 'https://socialuni.cn/',
    meta: { title: '文档' }
  },*/
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: constantRoutes
})


const whiteList = ['/login'] // no redirect whitelist


export default router
