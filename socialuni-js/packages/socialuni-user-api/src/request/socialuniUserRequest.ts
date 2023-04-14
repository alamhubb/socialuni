import SocialuniHttpRequest from "socialuni-base-api/src/request/SocialuniHttpRequest";
import SocialuniConfig from "socialuni-base-api/src/config/SocialuniConfigModule";

const socialuniUserRequest: SocialuniHttpRequest = new SocialuniHttpRequest()

/* 设置全局配置 */
socialuniUserRequest.setConfig(config => {
    config.baseUrl = SocialuniConfig.socialuniUserUrl
    return config
})

export default socialuniUserRequest
