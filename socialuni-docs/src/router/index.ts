import type {RouteRecordRaw} from "vue-router";
import {createRouter, createWebHistory} from 'vue-router'
import MarkdownVue from "@/components/MarkdownVue";

export const constantRouters: RouteRecordRaw[] = [
    {
        path: '/',
        name: 'home',
        meta: {title: '首页'},
        component: MarkdownVue(import( '@/views/home/home.md'))
        // component: import( '@/views/home/HomeView.vue')
    }
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: constantRouters
})

export default router
