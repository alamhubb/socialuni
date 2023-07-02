import {socialuniSystemModule} from "@socialuni/socialuni-util/src/store/SocialuniSystemModule";
import CommonEventUtil from "./util/CommonEventUtil";
import UniappPageLifecycleConst from "./UniappPageLifecycleConst";
import {socialuniPluginsModule} from "@socialuni/socialuni/src/store/SocialuniPluginsModule";
import {onBeforeMount} from "vue";
import PlatformModuleLoadUtil from "@socialuni/socialuni-native-util/src/util/PlatformModuleLoadUtil";


export async function onLoad(hook: (params: any) => any) {
    if (socialuniSystemModule.isUniApp) {
        //查询是否包含community模块，如果存在则加载
        try {
            const modules = import.meta.globEager('../../../node_modules/@dcloudio/uni-app/dist/uni-app.es.js')
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
