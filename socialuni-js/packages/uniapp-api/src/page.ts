import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import {nextTick, onBeforeMount, onMounted, onUnmounted} from "vue";
import {getCurrentRoute} from "./RouterUtil";


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
        const route = getCurrentRoute()
        console.log('zhixingle')
        console.log(route.value)
        console.log(route.value.query)
        for (const key in route.value.query) {
            console.log(route.value.query[key])
        }
        console.log(route.query)
        onMounted(() => {
            // router.currentRoute
            // hook()
        })
    }
}
