import SocialuniHttpRequest from "socialuni/src/request/SocialuniHttpRequest";
import SocialuniConfig from "socialuni/src/config/SocialuniConfig";

const socialuniCommunityRequest: SocialuniHttpRequest = new SocialuniHttpRequest()

/* 设置全局配置 */
socialuniCommunityRequest.setConfig(config => {
    config.baseUrl = SocialuniConfig.socialuniCommunityUrl+'/'
    return config
})

export default socialuniCommunityRequest
