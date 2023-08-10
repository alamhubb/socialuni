import MPUtil from "./MPUtil";
import APPUtil from "./APPUtil";

export default class SocialuniAppUniUtil {
    static checkUpdate() {
        console.log('checked update')
        // #ifdef MP
        MPUtil.checkUpdate()
        // #endif
        // #ifdef APP-PLUS
        APPUtil.checkUpdate()
        // #endif
    }
}
