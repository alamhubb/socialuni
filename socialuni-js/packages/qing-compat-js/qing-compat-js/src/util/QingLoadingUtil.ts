import PlatformModuleLoadUtil from "./PlatformModuleLoadUtil";
import {SocialuniLodingInterface} from "../interface/SocialuniLodingInterface";

export default class QingLoadingUtil {
    private static nativeUtilSelf = null

    static async init() {
        const modules = await PlatformModuleLoadUtil.dynamicImport('qing-compat-js', '/src/util/LoadingUtil.ts')
        this.nativeUtilSelf = modules.default
    }

    static get nativeUtil(): SocialuniLodingInterface {
        return this.nativeUtilSelf
    }
}

QingLoadingUtil.init()
