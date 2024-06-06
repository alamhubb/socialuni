// 1. 定义路由组件.
// 也可以从其他文件导入
import {createRouter, createWebHistory} from "vue-router";

import Layout from '@/layout/Layout.vue'
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import SocialuniTokenUtil from "socialuni-user-sdk/src/util/SocialuniTokenUtil";
import PeiwanManageView from '@/views/peiwanManage/PeiwanManageView.vue'
import CreateUserView from "@/views/user/CreateUser.vue";
import OperateUserManage from "@/views/operateUserManage/OperateUserManage.vue";

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
        path: '/operateUserManage',
        name: 'operateUserManage',
        component: OperateUserManage,
        meta: {title: '运营用户管理', icon: 'strengthMonitoring', isOpen: true}
    },
    // {
    //     path: '/peiwanManage',
    //     name: 'peiwanManage',
    //     component: PeiwanManageView,
    //     meta: {title: '陪玩管理', icon: 'strengthMonitoring', isOpen: true}
    // },
    // {
    //     path: '/customCreateUser',
    //     name: 'customCreateUser',
    //     component: CreateUserView,
    //     meta: {title: '创建用户', icon: 'strengthMonitoring', isOpen: true}
    // }
]

export const constantRoutes = [
    {
        path: '/404',
        component: () => import('@/views/404.vue'),
        meta: {hidden: true},
    },
    {
        path: '/login',
        component: () => import('@/views/login/login.vue'),
        meta: {hidden: true},
        hidden: true
    },
    {
        path: '/',
        component: Layout,
        redirect: '/peiwanManage',
        children: menuRoutes,
    },
    // 404 page must be placed at the end !!!
    {path: '/:pathMatch(.*)', redirect: '/404', hidden: true}
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
    history: createWebHistory(),
    routes: constantRoutes, // `routes: routes` 的缩写
})

const whiteList = ['/login'] // no redirect whitelist

router.beforeEach(async (to, from, next) => {
    // set page title
    let user = socialuniUserModule.mineUser
    console.log(user)
    const hasToken = socialuniUserModule.hasToken
    if (!user) {
        if (hasToken) {
            socialuniUserModule.getUserInfo()
        }
    }
    console.log(hasToken)
    if (hasToken) {
        if (to.path === '/login') {
            // if is logged in, redirect to the home page
            next({path: '/'})
        } else {
            console.log(to.path)
            next()
        }
    } else {
        /* has no token*/
        if (whiteList.indexOf(to.path) !== -1) {
            // in the free login whitelist, go directly
            next()
        } else {
            // other pages that do not have permission to access are redirected to the login page.
            next({path: '/login'})
        }
    }
})

router.afterEach(() => {
    // finish progress bar
})


export default router
