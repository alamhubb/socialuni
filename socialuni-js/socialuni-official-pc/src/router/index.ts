import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Layout from "@/layout/Layout.vue";
import ChatView from '@/views/ChatView.vue'

export const menuRoutes = [
  {
    path: '/',
    name: 'chat',
    component: ChatView,
    meta: {title: '首页', hidden: true},
  },
  {
    path: '/login',
    component: () => import('@/views/login/login.vue'),
    meta: {title: '登录', hidden: true},
    hidden: true
  },
  {
    path: '',
    name: 'home',
    component: HomeView,
    meta: {title: '首页', hidden: true},
  },
]


export const constantRoutes = [
  {
    path: '/',
    component: Layout,
    redirect: '/',
    children: menuRoutes
  },
  {
    path: '/404',
    component: () => import('@/views/404/404.vue'),
    hidden: true
  },
  // 404 page must be placed at the end !!!
  { path: '/:pathMatch(.*)', redirect: '/404', hidden: true }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: constantRoutes
})

export default router
