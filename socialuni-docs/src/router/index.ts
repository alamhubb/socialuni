import type {RouteRecordRaw} from "vue-router";
import {createRouter, createWebHistory} from 'vue-router'
import Layout from "@/layout/Layout.vue";
import HomeView from '@/views/home/HomeView.vue'
import Introduction from '@/views/guide/introduction/introduction.md'
import MoreUsage from '@/views/guide/moreUsage/moreUsage.md'
import DemoView from '@/views/demo/DemoView.vue'
import RouterConst from "@/router/RouterConst";

export const qingchiRouters: RouteRecordRaw[] = [
    {
        path: '',
        name: 'qingchi',
        meta: {title: '清池', hidden: true},
        component: () => import('@/views/qingchi/QingchiHomeView.vue')
    },
    {
        path: RouterConst.agreementName,
        name: RouterConst.agreementName,
        meta: {title: RouterConst.agreementTitle},
        component: () => import('@/views/qingchi/agreement/agreement.md')
    },
    {
        path: RouterConst.privacyName,
        name: RouterConst.privacyName,
        meta: {title: RouterConst.privacyTitle},
        component: () => import('@/views/qingchi/privacy/privacy.md')
    },
    {
        path: RouterConst.childProtectName,
        name: RouterConst.childProtectName,
        meta: {title: RouterConst.childProtectTitle},
        component: () => import('@/views/qingchi/childProtect/childProtect.md')
    },
    {
        path: RouterConst.suggestName,
        name: RouterConst.suggestName,
        meta: {title: RouterConst.suggestTitle},
        component: () => import('@/views/qingchi/suggest/suggest.md')
    },
    {
        path: RouterConst.contactName,
        name: RouterConst.contactName,
        meta: {title: RouterConst.contactTitle},
        component: () => import('@/views/qingchi/contact/contact.md')
    },
    {
        path: RouterConst.rulesName,
        name: RouterConst.rulesName,
        meta: {title: RouterConst.rulesTitle},
        component: () => import('@/views/qingchi/rules/rules.md')
    },
    {
        path: RouterConst.groupName,
        name: RouterConst.groupName,
        meta: {title: RouterConst.groupTitle},
        component: () => import('@/views/qingchi/group/group.md')
    },
    {
        path: RouterConst.officialName,
        name: RouterConst.officialName,
        meta: {title: RouterConst.officialTitle},
        component: () => import('@/views/qingchi/official/official.md')
    },
    {
        path: RouterConst.about,
        name: RouterConst.about,
        meta: {title: RouterConst.aboutTitle},
        component: () => import('@/views/qingchi/about/about.md')
    },
]

export const constantRouters: RouteRecordRaw[] = [
    {
        path: '/',
        component: Layout,
        meta: {hidden: true},
        children: [
            {
                path: '/',
                name: 'home',
                meta: {title: '首页'},
                component: HomeView,
            }
        ]
    },
    {
        path: '/guide',
        component: Layout,
        name: 'guide',
        meta: {title: '入门'},
        redirect: '/guide/introduction',
        children: [
            {
                path: 'introduction',
                name: 'introduction',
                meta: {title: '入门'},
                component: Introduction
            },
            {
                path: 'moreUsage',
                name: 'moreUsage',
                meta: {title: '更多用法'},
                component: MoreUsage
            }
        ]
        // component: import( '@/views/home/HomeView.vue')
    },
    {
        path: '/demo',
        component: Layout,
        meta: {title: 'demo'},
        redirect: '/demo',
        children: [
            {
                path: '',
                name: 'demo',
                meta: {title: 'demo'},
                component: DemoView
            }
        ]
    },
    {
        path: '/qingchi',
        component: Layout,
        meta: {title: '清池app'},
        redirect: '/qingchi',
        children: qingchiRouters
    }
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: constantRouters
})

export default router
