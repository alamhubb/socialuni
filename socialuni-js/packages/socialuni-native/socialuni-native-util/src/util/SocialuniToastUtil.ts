import PlatformModuleLoadUtil from "./PlatformModuleLoadUtil";
import {SocialuniToastUtilInterface} from "../interface/SocialuniToastUtilInterface";

export default class SocialuniToastUtil {
    private static nativeUtilSelf = null

    static async init() {
        const modules = await PlatformModuleLoadUtil.dynamicImport('@socialuni/socialuni-native', '/src/util/ToastUtil.ts')
        this.nativeUtilSelf = modules.default
    }

    static get nativeUtil(): SocialuniToastUtilInterface {
        return this.nativeUtilSelf
    }
}

SocialuniToastUtil.init()
