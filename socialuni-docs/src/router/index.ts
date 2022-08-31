import type {RouteRecordRaw} from "vue-router";
import {createRouter, createWebHistory} from 'vue-router'
import Layout from "@/layout/Layout.vue";
import HomeView from '@/views/home/HomeView.vue'
import Introduction from '@/views/guide/introduction/introduction.md'
import MoreUsage from '@/views/guide/moreUsage/moreUsage.md'

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
    }
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: constantRouters
})

export default router
