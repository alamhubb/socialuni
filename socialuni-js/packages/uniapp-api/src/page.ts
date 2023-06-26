import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import {nextTick, onBeforeMount, onMounted, onUnmounted} from "vue";
import {createRouter, createWebHistory} from "vue-router";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [],
})

export async function onLoad(hook: (params: any) => any) {
    console.log(socialuniSystemModule.isUniApp)


    if (socialuniSystemModule.isUniApp) {
        //查询是否包含community模块，如果存在则加载
        try {
            const {onLoad} = import.meta.globEager('/dcloudio/uni-app')
            onLoad(hook)
        } catch (e) {
            console.log(e)
        }
    } else {
        console.log('zhixingle')
        console.log(router.currentRoute)
        console.log(router.currentRoute.value)
        onMounted(() => {
            router.currentRoute
            hook()
        })
    }
}
