import PlatformModuleLoadUtil from "./PlatformModuleLoadUtil";
import {SocialuniRouterUtilInterface} from "../interface/SocialuniRouterUtilInterface";

let nativeUtil

const modules = import.meta.globEager('../../../*/src/util/RouterUtil.ts')

nativeUtil = PlatformModuleLoadUtil.getModule(modules)

export default class SocialuniRouterUtil {
    static get nativeUtil(): SocialuniRouterUtilInterface {
        return nativeUtil
    }
}
