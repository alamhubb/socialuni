import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import CommonEventUtil from "./util/CommonEventUtil";
import UniappPageLifecycle from "./UniappPage";


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
        CommonEventUtil.on(UniappPageLifecycle.onLoad,(params)=>{
            hook(params)
        })
    }
}
