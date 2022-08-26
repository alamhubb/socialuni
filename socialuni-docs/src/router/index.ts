import {createRouter, createWebHistory} from 'vue-router'
import HomeView1 from '@/views/home/home.md'
import HomeView from '@/views/home/HomeView.vue'
import type {RouteRecordRaw} from "vue-router";

export const constantRouters: RouteRecordRaw[] = [
    {
        path: '/',
        name: 'home',
        meta: {title: '首页'},
        component: HomeView
    }
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: constantRouters
})

export default router
