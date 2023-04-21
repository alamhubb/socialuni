console.log(8888)

import SocialuniHttpRequest from "socialuni/src/request/SocialuniHttpRequest";
import SocialuniConfig from "socialuni/src/config/SocialuniConfig";


console.log(54444)

const socialuniUserRequest: SocialuniHttpRequest = new SocialuniHttpRequest()

/* 设置全局配置 */
socialuniUserRequest.setConfig(config => {
    config.baseUrl = SocialuniConfig.socialuniUserUrl + '/'
    return config
})

export default socialuniUserRequest
