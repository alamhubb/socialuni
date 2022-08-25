import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/home/HomeView.md'
import Layout from '@/layout/Layout.vue'

export const menuRoutes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: Layout,
      redirect: '/',
      children: menuRoutes
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
})

export default router
