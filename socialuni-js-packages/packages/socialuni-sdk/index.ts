import SocialuniConfig from "./src/SocialuniConfig";
import {initSocialuniStore, socialAppModule, socialSystemModule} from "./src/store/store";
import UniUtil from "./src/utils/UniUtil";

let socialuniSdkConfig = null

const Socialuni = {
    install(vue, options?: SocialuniConfig) {
        socialuniSdkConfig = new SocialuniConfig(options)
        initSocialuniStore()
        socialSystemModule.initSystemInfo()
        //如果有跳转信息
        socialAppModule.appLunchAction()
        UniUtil.showShareMenu()
    }
}
export default Socialuni
