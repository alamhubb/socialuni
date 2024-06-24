import PlatformModuleLoadUtil from "./PlatformModuleLoadUtil";
import {SocialuniRouterUtilInterface} from "../interface/SocialuniRouterUtilInterface";

let nativeUtil

const modules = import.meta.glob('../../../*/src/util/RouterUtil.ts',{eager:true})

nativeUtil = PlatformModuleLoadUtil.getModuleDefault(modules)

export default class QingRouterUtil {
    static get nativeUtil(): SocialuniRouterUtilInterface {
        return nativeUtil
    }
}
