import Vue from 'vue'
import VueRouter from 'vue-router'
/* Layout */
import Layout from '@/components/layout/Layout.vue'
import RouterName from "@/constants/RouterName";

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
        hidden: false,
        showFirstChild: true,
        redirect: '/login',
        children: [
            {
                path: '',
                name: 'strengthMonitoring',
                component: () => import('@/views/login/Login.vue'),
                meta: {title: '登陆', icon: 'strengthMonitoring'}
            }]
    },
    {
        path: '/',
        component: Layout,
        redirect: '/',
        children: [
            /*{
                path: '',
                name: 'strengthMonitoring',
                component: () => import('@/views/home/Home.vue'),
                meta: {title: '首页', icon: 'strengthMonitoring'}
            },*/
            {
                path: '',
                name: RouterName.contentAudit,
                // redirect: '/review',
                component: () => import('@/views/audit/contentAudit.vue'),
                // children: showRouterList,
            },
            {
                path: '/' + RouterName.auditHistory,
                name: RouterName.auditHistory,
                // redirect: '/review',
                component: () => import('@/views/audit/auditHistory.vue'),
                // children: showRouterList,
            },
            {
                path: '/keywordsManage',
                name: RouterName.keywordsManage,
                component: () => import('@/views/keywordsManage/keywordsManage.vue')
            },
            {
                path: '/test',
                name: '测试',
                component: () => import('@/views/testTemp/testTemp.vue')
            }]
    },
    {
        path: 'https://shejiao.qingchiapp.com/',
        meta: {title: '文档'}
    },
    // 404 page must be placed at the end !!!
    {path: '*', redirect: '/404', hidden: true}
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: constantRoutes
})

router.beforeEach(async (route, from, next) => {
    // start progress bar
    console.log(route.path)
    next()
})

export default router
