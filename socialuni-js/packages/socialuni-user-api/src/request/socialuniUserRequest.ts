import SocialuniHttpRequest from "socialuni-base-api/src/request/SocialuniHttpRequest";
import {socialuniConfigModule} from "socialuni-base-api/src/store/SocialuniConfigModule";

const socialuniUserRequest: SocialuniHttpRequest = new SocialuniHttpRequest()

/* 设置全局配置 */
socialuniUserRequest.setConfig(config => {
    config.baseUrl = socialuniConfigModule.socialuniUserUrl
    return config
})

export default socialuniUserRequest
