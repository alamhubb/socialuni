import SocialuniConfig from "./src/SocialuniConfig";
import UniUtil from "../socialuni-user/src/utils/UniUtil";

let socialuniSdkConfig = null

const SocialuniIM = {
    install(vue, options?: SocialuniConfig) {
        socialuniSdkConfig = new SocialuniConfig(options)
        initSocialuniStore()
        UniUtil.showShareMenu()
    }
}
export default SocialuniIM
