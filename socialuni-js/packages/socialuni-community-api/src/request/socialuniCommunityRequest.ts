import SocialuniHttpRequest from "socialuni-base-api/src/request/SocialuniHttpRequest";
import SocialuniConfig from "socialuni-base-api/src/config/SocialuniConfigModule";

const socialuniCommunityRequest: SocialuniHttpRequest = new SocialuniHttpRequest()

/* 设置全局配置 */
socialuniCommunityRequest.setConfig(config => {
    config.baseUrl = SocialuniConfig.socialuniCommunityUrl+'/'
    return config
})

export default socialuniCommunityRequest
