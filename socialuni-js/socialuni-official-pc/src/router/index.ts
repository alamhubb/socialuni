import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Layout from "@/layout/Layout.vue";

export const menuRoutes = [
  {
    path: '',
    name: 'home',
    component: HomeView,
    meta: {title: '首页', hidden: true},
  },
  {
    path: '',
    name: 'home',
    component: HomeView,
    meta: {title: '首页', hidden: true},
  },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: Layout,
      redirect: '/',
      children: menuRoutes
    }
  ]
})

export default router
