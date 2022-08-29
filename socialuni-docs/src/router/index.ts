import type {RouteRecordRaw} from "vue-router";
import {createRouter, createWebHistory} from 'vue-router'

export const constantRouters: RouteRecordRaw[] = [
    {
        path: '/',
        name: 'home',
        meta: {title: '首页'},
        component: () => import( '@/views/home/HomeView.vue')
        // component: import( '@/views/home/HomeView.vue')
    }
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: constantRouters
})

export default router
