import SocialuniHttpRequest from "./SocialuniHttpRequest";
import SocialuniConfig from "socialuni-common/src/config/SocialuniConfig";

const socialuniUserRequest: SocialuniHttpRequest = new SocialuniHttpRequest()
socialuniUserRequest.setConfig(config => { /* 设置全局配置 */
    config.baseUrl = SocialuniConfig.socialuniUrl
    config.timeout = 10 * 1000
    return config
})
export default socialuniUserRequest
