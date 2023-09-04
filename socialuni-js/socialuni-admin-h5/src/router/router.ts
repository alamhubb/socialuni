// 1. 定义路由组件.
// 也可以从其他文件导入
import {createRouter, createWebHashHistory} from "vue-router";

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
        meta: {title: '陪玩管理', icon: 'strengthMonitoring'}
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
        redirect: '/peiwanManage',
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

console.log(constantRoutes)

// 3. 创建路由实例并传递 `routes` 配置
// 你可以在这里输入更多的配置，但我们在这里
// 暂时保持简单
const router = createRouter({
    // 4. 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
    history: createWebHashHistory(),
    routes: constantRoutes, // `routes: routes` 的缩写
})

export default router
