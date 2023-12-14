import {SocialuniAlertUtilInterface} from "../interface/SocialuniAlertUtilInterface";
import PlatformModuleLoadUtil from "./PlatformModuleLoadUtil";

export default class QingAlertUtil {
    private static nativeUtilSelf = null

    static async init() {
        const modules = await PlatformModuleLoadUtil.dynamicImport('qingjs', '/src/util/AlertUtil.ts')
        this.nativeUtilSelf = modules.default
    }

    static get nativeUtil(): SocialuniAlertUtilInterface {
        return this.nativeUtilSelf
    }
}

QingAlertUtil.init()
