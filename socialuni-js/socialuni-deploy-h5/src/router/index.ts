import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import MessageView from "@/views/chat/MessageView.vue";
import LoginPage from "@/views/user/LoginPage.vue";

export const constantRoutes = [
    {
        path: '/',
        name: 'message',
        component: LoginPage,
        meta: {title: '消息', hidden: true},
    },
    {
        path: '/home',
        name: 'home',
        component: HomeView,
        meta: {title: '首页', hidden: true},
    },
    {
        path: '/mine',
        name: 'mine',
        component: () => import('@/views/user/mine.vue'),
        meta: {title: '我的', hidden: true},
    },
    {
        path: '/404',
        component: () => import('@/views/404/404.vue'),
        hidden: true
    },
    // 404 page must be placed at the end !!!
    {path: '/:pathMatch(.*)', redirect: '/404', hidden: true}
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: constantRoutes
})

export default router
