import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import CommonEventUtil from "./util/CommonEventUtil";
import UniappPageLifecycleConst from "./UniappPageLifecycleConst";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import {onBeforeMount} from "vue";


export async function onLoad(hook: (params: any) => any) {
    if (socialuniSystemModule.isUniApp) {
        //查询是否包含community模块，如果存在则加载
        try {
            const {onLoad} = import.meta.globEager('/dcloudio/uni-app')
            onLoad(hook)
        } catch (e) {
            console.log(e)
        }
    } else {
        onBeforeMount(()=>{
            hook(socialuniPluginsModule.route.query)
        })
    }
}
