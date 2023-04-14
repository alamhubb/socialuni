import SocialuniRequestHeaderName from "socialuni-constant/constant/SocialuniRequestHeaderName";
import {socialuniUserData} from "../socialuniData/SocialuniUserModule";
import {socialuniConfigModule} from "../socialuniData/SocialuniConfigModule";
import {socialuniSystemModule} from "../socialuniData/SocialuniSystemModule";
import UniUtil from "socialuni-sdk/src/utils/UniUtil";
import ErrorConst from "socialuni-constant/constant/ErrorConst";
import UserService from "socialuni-sdk/src/service/UserService";
import MsgUtil from "socialuni-sdk/src/utils/MsgUtil";
import AlertUtil from "socialuni-sdk/src/utils/AlertUtil";
import ObjectUtil from "socialuni-sdk/src/utils/ObjectUtil";
import UniAppHttpRequest, {Config, Interceptor, RequestConfig, Response} from "./UniAppHttpRequest";
import SocialuniAppAPI from "../api/socialuni/SocialuniAppAPI";

export default class SocialuniHttpRequest extends UniAppHttpRequest {
    config: Config = {
        baseUrl: socialuniConfigModule.socialuniUrl,
        timeout: 10 * 1000,
        header: {
            'Content-Type': 'application/json'
        },
        method: 'GET',
        dataType: 'json',
        responseType: 'text'
    }

    protected requestBeforeFun: (config: RequestConfig) => RequestConfig = (config: RequestConfig) => {
        const token = socialuniUserData.token
        if (token) {
            config.header.token = token
            //下次尝试把这里删除
        } else {
            config.header.token = null
        }
        // 如果配置了开发环境，就可以展示具体的报错内容。
        config.header['X-NODE-ENV'] = process.env.NODE_ENV
        //
        if (socialuniConfigModule.socialuniSecretKey) {
            config.header.socialuniSecretKey = socialuniConfigModule.socialuniSecretKey
        }
        config.header[SocialuniRequestHeaderName.system] = socialuniSystemModule.system
        config.header[SocialuniRequestHeaderName.platform] = socialuniSystemModule.platform
        config.header[SocialuniRequestHeaderName.provider] = socialuniSystemModule.provider
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
    }

    protected requestComFun: (response: Response) => any = (response: Response) => {
        return response.data
    }

    protected requestComFail: (response: Response) => Response = (error: Response) => {
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

    interceptor: Interceptor = {
        request: (callback: (config: RequestConfig) => RequestConfig) => {
            const oldFun = this.requestBeforeFun
            this.requestBeforeFun = (config: RequestConfig) => {
                const oldConfig = oldFun(config)
                return callback(oldConfig)
            }
        },
        response: (cb: (rep: Response) => any, ecb: (rep: Response) => any) => {
            this.requestComFun = cb

            const oldFun = this.requestComFail
            this.requestComFail = (config: Response) => {
                const oldConfig = oldFun(config)
                return ecb(oldConfig)
            }
        }
    }
}
