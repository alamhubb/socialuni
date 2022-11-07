import HttpRequest, {requestConfig} from "./httpRequest";
import {socialSystemModule, socialUserModule} from "socialuni-sdk/src/store/store";
import TokenUtil from "socialuni-sdk/src/utils/TokenUtil";
import ErrorConst from "socialuni-constant/constant/ErrorConst";
import UserService from "socialuni-sdk/src/service/UserService";
import MsgUtil from "socialuni-sdk/src/utils/MsgUtil";
import UniUtil from "socialuni-sdk/src/utils/UniUtil";
import AlertUtil from "socialuni-sdk/src/utils/AlertUtil";
import SocialuniAppAPI from "../api/socialuni/SocialuniAppAPI";
import XmlResultRO from "../model/base/XmlResultRO";
import {socialConfigModule} from "socialuni-sdk/src/store/store";
import ObjectUtil from "socialuni-sdk/src/utils/ObjectUtil";
import XmlUtil from "../utils/XmlUtil";

const request: HttpRequest = new HttpRequest()

const socialHttpUrl = import.meta.env.VITE_APP_BASE_URL || 'https://api.socialuni.cn'
// const socialHttpUrl = 'https://api.socialuni.cn'

request.setConfig(config => { /* 设置全局配置 */
    config.baseUrl = socialHttpUrl + '/'/* 根域名不同 */
    config.timeout = 10 * 1000
    return config
})
request.interceptor.request((config: requestConfig) => { /* 请求之前拦截器 */
    const token = socialUserModule.token
    if (token) {
        config.header.token = token
        //下次尝试把这里删除
    } else {
        config.header.token = null
    }
    // config.header.socialuniSecretKey = socialConfig.secretKey
    config.header.provider = socialSystemModule.mpPlatform
    config.header.platform = socialSystemModule.platform
    config.header.system = socialSystemModule.system

    /* else {
      //如果未登录，只允许查询talk，其他全部提示要登录
      constant configUrl: string = config.url
      if (configUrl.indexOf('queryTalks') < 0 && configUrl.indexOf('wxLogin') < 0) {
        MsgUtil.unLoginMessage()
        cancel('用户未登录请求了没有权限的接口', config)
      }
    } */
    return config
})

request.interceptor.response(

)
export default request
