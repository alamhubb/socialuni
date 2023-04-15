import SocialuniConfig from "packages/socialuni-base-api/src/config/SocialuniConfig";
import SocialuniHttpRequest from "socialuni-base/src/request/SocialuniHttpRequest";

const socialuniImRequest: SocialuniHttpRequest = new SocialuniHttpRequest()

/* 设置全局配置 */
socialuniImRequest.setConfig(config => {
    config.baseUrl = SocialuniConfig.socialuniImUrl + '/'
    return config
})

export default socialuniImRequest
