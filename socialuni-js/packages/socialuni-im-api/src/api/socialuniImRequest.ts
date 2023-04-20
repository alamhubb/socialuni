import SocialuniHttpRequest from "socialuni-base-api/src/request/SocialuniHttpRequest";
import SocialuniConfig from "socialuni-base-api/src/config/SocialuniConfig";

const socialuniImRequest: SocialuniHttpRequest = new SocialuniHttpRequest()

/* 设置全局配置 */
socialuniImRequest.setConfig(config => {
    config.baseUrl = SocialuniConfig.socialuniImUrl + '/'
    return config
})

export default socialuniImRequest
