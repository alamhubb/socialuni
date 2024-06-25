import HomeView from '@/views/HomeView.vue'
import MessageView from "@/views/chat/MessageView.vue";
// import {createRouter, createWebHistory} from "@/plugins/router/src/index.ts";
import {createRouter, createWebHistory} from "vue-router";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule.ts";
// import MessageView from "@/views/chat/MessageView.tsx";

export const constantRoutes = [
    // {
    //     path: '/home',
    //     name: 'home',
    //     component: HomeView,
    //     meta: {title: '首页'},
    // },
    // {
    //     path: '/',
    //     name: 'message',
    //     component: MessageView,
    //     meta: {title: '消息'},
    // },
    // {
    //     path: '/mine',
    //     name: 'mine',
    //     component: () => import('@/views/user/mine.vue'),
    //     meta: {title: '我的'},
    // },
    {
        path: '/404',
        component: () => import('@/views/404/404.vue'),
        meta: {hidden: true},
    },
    // 404 page must be placed at the end !!!
    {path: '/:pathMatch(.*)', redirect: '/404', meta: {hidden: true}}
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: constantRoutes
})


router.beforeEach(async (to, from, next) => {
    console.log('fasdfasdfasdfasdf')
    console.log(to.path)
    console.log(from.path)
    next()
})


export default router
