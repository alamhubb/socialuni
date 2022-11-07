import request from "socialuni-common/src/request/request";
import SocialuniConfig from "socialuni-sdk/src/SocialuniConfig";

const request =

request.setConfig(config => { /* 设置全局配置 */
    config.baseUrl = SocialuniConfig.socialuniImUrl
    config.timeout = 10 * 1000
    return config
})

