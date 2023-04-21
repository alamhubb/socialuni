console.log(8888)

import SocialuniHttpRequest from "socialuni/src/request/SocialuniHttpRequest";
import SocialuniConfig from "socialuni/src/config/SocialuniConfig";

const socialuniImRequest: SocialuniHttpRequest = new SocialuniHttpRequest()

/* 设置全局配置 */
socialuniImRequest.setConfig(config => {
    config.baseUrl = SocialuniConfig.socialuniImUrl + '/'
    return config
})

export default socialuniImRequest
