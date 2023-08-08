import {socialuniSystemModule} from "@socialuni/socialuni-util/src/store/SocialuniSystemModule";
import {socialuniPluginsModule} from "@socialuni/socialuni/src/store/SocialuniPluginsModule";
import PlatformModuleLoadUtil from "@socialuni/socialuni-native-util/src/util/PlatformModuleLoadUtil";
import {getCurrentInstance, onBeforeMount} from "vue";

export async function onLoad(hook: (params: any) => any, target = getCurrentInstance()) {
    if (socialuniSystemModule.isUniApp) {
        //查询是否包含community模块，如果存在则加载
        try {
            const modules = await import('../../../node_modules/@dcloudio/uni-app/dist/uni-app.es.js')
            const onLoad1 = modules.onLoad
            console.log('zhixingle')
            console.log(onLoad1)
            onLoad1(hook, target)
        } catch (e) {
            console.log(e)
        }
    } else {
        onBeforeMount(() => {
            hook(socialuniPluginsModule.route.query)
        })
    }
}
