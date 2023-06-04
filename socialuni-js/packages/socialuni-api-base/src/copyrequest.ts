import {socialuniPluginsModule} from "socialuni-base/src/store/SocialuniPluginsModule";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import SocialuniConfig from "socialuni/src/config/SocialuniConfig";
import SocialuniRequestHeaderName from "socialuni-constant/constant/SocialuniRequestHeaderName";
import UniUtil from "socialuni-util/src/util/UniUtil";
import ErrorConst from "socialuni-constant/constant/ErrorConst";
import UserService from "socialuni/src/service/UserService";
import UserMsgUtil from "socialuni/src/util/UserMsgUtil";
import AlertUtil from "socialuni-util/src/util/AlertUtil";
import SocialuniAppAPI from "socialuni/src/api/SocialuniAppAPI";
import MsgUtil from "socialuni-util/src/util/MsgUtil";
import JsonUtil from "socialuni/src/util/JsonUtil";

socialuniAxios.interceptors.request.use(
    config => {
        for (const socialuniPlugin of socialuniPluginsModule.plugins) {
            socialuniPlugin.onRequestInterceptors().request.use(config)
        }

        const token = TokenUtil.get()
        if (token) {
            config.headers.token = token
        } else {
            config.headers.token = null
        }
        // 如果配置了开发环境，就可以展示具体的报错内容。
        config.header['X-NODE-ENV'] = socialuniSystemModule.NODE_ENV
        //
        if (SocialuniConfig.socialuniSecretKey) {
            config.header.socialuniSecretKey = SocialuniConfig.socialuniSecretKey
        }
        config.header[SocialuniRequestHeaderName.system] = socialuniSystemModule.system
        config.header[SocialuniRequestHeaderName.platform] = socialuniSystemModule.platform
        config.header[SocialuniRequestHeaderName.provider] = socialuniSystemModule.provider
        // do something before request is sent
        return config
    },
    error => {
        return Promise.reject(error)
    }
)

socialuniAxios.interceptors.response.use(
    /**
     * If you want to get http information such as headers or status
     * Please return  response => response
     */

    /**
     * Determine the request status by custom code
     * Here is just an example
     * You can also judge the status by HTTP Status Code
     */
    response => {
        return response.data
    },
    response => {

        for (const socialuniPlugin of socialuniPluginsModule.plugins) {

        }
        UniUtil.hideLoading()
        //第一步，先判断 有没有error
        //判断data类型，如果没类型，直接走
        //有内容则为本系统？也不一定，判断拿内容类型
        //然后本系统处理
        if (response && response.statusCode) {
            const result = response.data
            if (result) {
                if (!result.errorMsg) {

                }
            }
        }
        UniUtil.hideLoading()
        const result = response.data
        if (response.statusCode && result) {
            const errorMsg = result.errorMsg
            if (errorMsg) {
                switch (response.statusCode) {
                    case ErrorConst.not_logged:
                        // 理论上不需要，因为token不会失效，也不会错误
                        // 已知可能，切换环境导致token不同
                        UserService.clearUserInfo()
                        UserMsgUtil.unLoginMessage()
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
                        SocialuniAppAPI.sendErrorLogAPI(response.config.url, errorMsg)
                        break
                }
            } else {
                MsgUtil.systemErrorMsg()
                SocialuniAppAPI.sendErrorLogAPI(response.config.url, result)
            }
            // 返回接口返回的错误信息
            return result
        }
        MsgUtil.systemErrorMsg()
        SocialuniAppAPI.sendErrorLogAPI(response.config.url, JsonUtil.toJson(response))
        return response
    }
)
