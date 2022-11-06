import SocialuniConfig from "./src/SocialuniConfig";
import {initSocialuniStore, socialAppModule, socialSystemModule} from "./src/store/store";
import UniUtil from "./src/utils/UniUtil";

let socialuniSdkConfig = null

const Socialuni = {
    install(vue, options?: SocialuniConfig) {
        socialuniSdkConfig = new SocialuniConfig(options)
        initSocialuniStore()
        UniUtil.showShareMenu()
    }
}
export default Socialuni
