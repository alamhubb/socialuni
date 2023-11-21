import {SocialuniAlertUtilInterface} from "../interface/SocialuniAlertUtilInterface";
import PlatformModuleLoadUtil from "./PlatformModuleLoadUtil";

export default class SocialuniAlertUtil {
    private static nativeUtilSelf = null

    static async init() {
        const modules = await PlatformModuleLoadUtil.dynamicImport('socialuni-native', '/src/util/AlertUtil.ts')
        this.nativeUtilSelf = modules.default
    }

    static get nativeUtil(): SocialuniAlertUtilInterface {
        return this.nativeUtilSelf
    }
}

SocialuniAlertUtil.init()
