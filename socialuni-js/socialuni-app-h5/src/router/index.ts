import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
export const constantRoutes = [
    {
        path: '/',
        name: 'home',
        component: HomeView,
        meta: {title: '主页', hidden: true},
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
