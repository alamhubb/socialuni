import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import DeployPage from '@/views/deploy/DeployPage.vue'

export const constantRoutes = [
  {
    path: '/',
    name: 'deploy',
    component: DeployPage,
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
  { path: '/:pathMatch(.*)', redirect: '/404', hidden: true }
]

const baseUrl = import.meta.env.BASE_URL

console.log(baseUrl)

const router = createRouter({
  history: createWebHistory(baseUrl),
  routes: constantRoutes
})

export default router
