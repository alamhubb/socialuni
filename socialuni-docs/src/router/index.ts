import {createRouter, createWebHistory} from 'vue-router'
import HomeView from '@/views/home/home.md'
import Layout from '@/layout/Layout.vue'
import type {RouteRecordRaw} from "vue-router";

export const constantRouters: RouteRecordRaw[] = [
    {
        path: '/',
        name: 'home',
        meta: {title: '首页'},
        component: HomeView
    },
    {
        path: '/guide',
        name: 'guide',
        meta: {title: '指南'},
        component: Layout,
        redirect: '/guide/introduction',
        children: [
            {
                path: 'introduction',
                name: 'introduction',
                meta: {title: '入门'},
                component: () => import('@/views/guide/introduction/introduction.md')
            },
            {
                path: 'moreUsage',
                name: 'moreUsage',
                meta: {title: '更多用发'},
                component: () => import('@/views/guide/moreUsage/moreUsage.md')
            }
        ]
    },
    /*{
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    }*/
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: constantRouters
})

export default router
