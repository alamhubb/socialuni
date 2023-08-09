import MPUtil from "./MPUtil";
import APPUtil from "./APPUtil";

export default class SocialuniAppUniUtil {
    static checkUpdate() {
        // #ifdef MP
        MPUtil.checkUpdate()
        // #endif
        // #ifdef APP-PLUS
        APPUtil.checkUpdate()
        // #endif
    }
}
