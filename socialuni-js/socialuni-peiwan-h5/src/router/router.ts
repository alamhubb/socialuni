// 1. 定义路由组件.
// 也可以从其他文件导入
import {createRouter,  createWebHistory} from "vue-router";

export const constantRoutes = [
    {
        path: '/404',
        component: () => import('@/views/404.vue'),
        hidden: true
    },
    {
        path: '/login',
        component: () => import('@/views/login/login.vue'),
        hidden: true
    },  {
        path: '/',
        component: () => import('@/views/home/HomePage.vue'),
        hidden: true
    },
    // 404 page must be placed at the end !!!
    {path: '/:pathMatch(.*)', redirect: '/404', hidden: true}
]

console.log(constantRoutes)


// 3. 创建路由实例并传递 `routes` 配置
// 你可以在这里输入更多的配置，但我们在这里
// 暂时保持简单
const router = createRouter({
    // 4. 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
    history: createWebHistory(),
    routes: constantRoutes, // `routes: routes` 的缩写
})

const whiteList = ['/login'] // no redirect whitelist


export default router
