import HomeView from '@/views/HomeView.vue'
import MessageView from "@/views/chat/MessageView.vue";
// import {createRouter, createWebHistory} from "@/plugins/router/src/index.ts";
import {createRouter, createWebHistory} from "vue-router";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule.ts";
import SocialuniTalkViewH5 from "socialuni-community-view-h5/src/views/SocialuniTalkViewH5.vue";
// import MessageView from "@/views/chat/MessageView.tsx";
import SocialuniChatMessageViewH5 from "socialuni-im-view-h5/src/views/SocialuniChatMessageViewH5.vue";
import SocialuniUserDetailViewH5 from "socialuni-user-view-h5/src/views/SocialuniUserDetailViewH5.vue";
import MusicHome from "@/views/music/MusicHome.vue";
import BeastChess from "@/views/beastChess/BeastChess.vue";
import SocialuniChatPage from "@/views/chat/SocialuniChatPage.vue";

export const constantRoutes = [
    {
        path: '/',
        name: '首页',
        component: SocialuniChatPage,
        meta: {title: '音乐'},
    },
    {
        path: '/message',
        name: '消息',
        component: SocialuniChatPage,
        meta: {title: '音乐'},
    },
    /*{
        path: '/userDetail',
        name: '用户详情',
        component: SocialuniUserDetailViewH5,
        meta: {title: '用户详情'},
    },
    {
        path: '/123',
        name: '斗兽棋',
        component: BeastChess,
        meta: {title: '斗兽棋'},
    },*/
    {
        path: '/talk',
        name: '社区',
        component: SocialuniTalkViewH5,
        meta: {title: '社区'},
    },
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
