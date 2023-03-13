import HttpRequest, {requestConfig} from "./httpRequest"
import {socialSystemModule} from "socialuni-utils/src/store/store"
import {socialUserModule} from "socialuni-sdk/src/store/store"
import ErrorConst from "socialuni-common-constant/constant/ErrorConst"
import UserService from "socialuni-sdk/src/service/UserService"
import MsgUtil from "socialuni-sdk/src/utils/MsgUtil"
import UniUtil from "socialuni-sdk/src/utils/UniUtil"
import AlertUtil from "socialuni-sdk/src/utils/AlertUtil"
import ObjectUtil from "socialuni-sdk/src/utils/ObjectUtil"
import SocialuniConfig from "../../../src/config/SocialuniConfig";
import SocialuniAppAPI from "../../../src/api/socialuni/SocialuniAppAPI";
import SocialuniRequestHeaderName from "socialuni-constant/constant/SocialuniRequestHeaderName";

const request: HttpRequest = new HttpRequest()

// const socialHttpUrl = 'https://api.socialuni.cn'

request.setConfig(config => { /* 设置全局配置 */
    config.baseUrl = SocialuniConfig.socialuniUrl
    config.timeout = 10 * 1000
    return config
})
request.interceptor.request((config: requestConfig) => { /* 请求之前拦截器 */
    const token = socialUserModule?.token
    if (token) {
        config.header.token = token
        //下次尝试把这里删除
    } else {
        config.header.token = null
    }
    // 如果配置了开发环境，就可以展示具体的报错内容。
    config.header['X-NODE-ENV'] = process.env.NODE_ENV
    //
    if (SocialuniConfig.socialuniSecretKey) {
        config.header.socialuniSecretKey = SocialuniConfig.socialuniSecretKey
    }
    config.header[SocialuniRequestHeaderName.system] = socialSystemModule.system
    config.header[SocialuniRequestHeaderName.platform] = socialSystemModule.platform
    config.header[SocialuniRequestHeaderName.provider] = socialSystemModule.provider

    //放user里面
    /*if (socialLocationModule.location && socialLocationModule.location.position) {
        config.header[SocialuniRequestHeaderName.socialuniCityAdCode] = socialLocationModule.location.adCode
        config.header[SocialuniRequestHeaderName.socialuniCityLon] = socialLocationModule.location.lon
        config.header[SocialuniRequestHeaderName.socialuniCityLat] = socialLocationModule.location.lat
    }*/

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
    response => {
        return response.data
    },
    error => {
        //第一步，先判断 有没有error
        //判断data类型，如果没类型，直接走
        //有内容则为本系统？也不一定，判断拿内容类型
        //然后本系统处理
        if (error && error.statusCode) {
            const result = error.data
            if (result) {
                if (!result.errorMsg) {

                }
            }
        }
        UniUtil.hideLoading()
        const result = error.data
        if (error.statusCode && result) {
            const errorMsg = result.errorMsg
            if (errorMsg) {
                switch (error.statusCode) {
                    case ErrorConst.not_logged:
                        // 理论上不需要，因为token不会失效，也不会错误
                        // 已知可能，切换环境导致token不同
                        UserService.clearUserInfo()
                        MsgUtil.unLoginMessage()
                        break
                    case ErrorConst.banned:
                        // 理论上不需要，因为token不会失效，也不会错误
                        // 已知可能，切换环境导致token不同
                        UserService.clearUserInfo()
                        AlertUtil.hint(errorMsg)
                        break
                    case ErrorConst.custom:
                        break
                    default:
                        AlertUtil.hint(errorMsg)
                        SocialuniAppAPI.sendErrorLogAPI(error.config.url, errorMsg)
                        break
                }
            } else {
                MsgUtil.systemErrorMsg()
                SocialuniAppAPI.sendErrorLogAPI(error.config.url, result)
            }
            // 返回接口返回的错误信息
            return result
        }
        MsgUtil.systemErrorMsg()
        SocialuniAppAPI.sendErrorLogAPI(error.config.url, ObjectUtil.toJson(error))
        return error
    }
)
export default request
