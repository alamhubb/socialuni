import {SocialuniImPagePathInterface} from "../constant/SocialuniImPagePathInterface";
import PlatformModuleLoadUtil from "@socialuni/socialuni-native-util/src/util/PlatformModuleLoadUtil";

export default class SocialuniImTopLevelAwaitUtil {
    private static _socialuniImPagePath: SocialuniImPagePathInterface = null

    static async getSocialuniImPagePath(): Promise<SocialuniImPagePathInterface> {
        if (!this._socialuniImPagePath) {
            const module = await PlatformModuleLoadUtil.dynamicImport('@socialuni/socialuni-im-view', 'src/constant/SocialuniImPagePath.ts')
            this._socialuniImPagePath = module.default
        }
        return this._socialuniImPagePath
    }
}
