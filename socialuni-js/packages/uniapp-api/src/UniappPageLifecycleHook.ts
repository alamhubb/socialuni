import {socialuniSystemModule} from "@socialuni/socialuni-util/src/store/SocialuniSystemModule";
import {socialuniPluginsModule} from "@socialuni/socialuni/src/store/SocialuniPluginsModule";
import PlatformModuleLoadUtil from "@socialuni/socialuni-native-util/src/util/PlatformModuleLoadUtil";
import {onBeforeMount} from "vue";

export async function onLoad(hook: (params: any) => any) {
    if (socialuniSystemModule.isUniApp) {
        //查询是否包含community模块，如果存在则加载
        try {
            const modules = await import('../../../node_modules/@dcloudio/uni-app/dist/uni-app.es.js')
            const {onLoad} = PlatformModuleLoadUtil.getFirstModule(modules)
            onLoad(hook)
        } catch (e) {
            console.log(e)
        }
    } else {
        onBeforeMount(() => {
            hook(socialuniPluginsModule.route.query)
        })
    }
}
