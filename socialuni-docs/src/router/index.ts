import type {RouteRecordRaw} from "vue-router";
import {createRouter, createWebHistory} from 'vue-router'
import Layout from "@/layout/Layout.vue";

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
                component: import( '@/views/home/HomeView.vue'),
            }
        ]
    },
    {
        path: '/guide',
        component: Layout,
        meta: {title: '入门'},
        redirect: '/guide/introduction',
        children: [
            {
                path: 'introduction',
                name: 'introduction',
                meta: {title: '入门'},
                component: import('@/views/guide/introduction/introduction.md')
            },
            {
                path: 'moreUsage',
                name: 'moreUsage',
                meta: {title: '更多用法'},
                component: import('@/views/guide/moreUsage/moreUsage.md')
            }
        ]

        // component: import( '@/views/home/HomeView.vue')
    }
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: constantRouters
})

export default router
