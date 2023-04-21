import UniUtil from "socialuni-util/src/util/UniUtil"
import { Config, RequestConfig, Response } from "./UniAppHttpRequest"
import HttpRequestConfig from './HttpRequestConfig'
import SocialuniConfig from '../config/SocialuniConfig'
import { socialuniSystemModule } from 'socialuni-util/src/store/SocialuniSystemModule'
import SocialuniRequestHeaderName from 'socialuni-constant/constant/SocialuniRequestHeaderName'
import ErrorConst from 'socialuni-constant/constant/ErrorConst'
import UserService from '../service/UserService'
import UserMsgUtil from '../util/UserMsgUtil'
import AlertUtil from 'socialuni-util/src/util/AlertUtil'
import MsgUtil from 'socialuni-util/src/util/MsgUtil'

export default class SocialuniHttpRequestConfig extends HttpRequestConfig {
  override getConfig(): Config {
    const config = super.getConfig()
    config.baseUrl = SocialuniConfig.socialuniUrl + '/'
    return config
  }

  override requestBefore: (config: RequestConfig) => RequestConfig = (config: RequestConfig) => {
    super.requestBefore(config)
    // 如果配置了开发环境，就可以展示具体的报错内容。
    config.header['X-NODE-ENV'] = socialuniSystemModule.NODE_ENV
    //
    if (SocialuniConfig.socialuniSecretKey) {
      config.header.socialuniSecretKey = SocialuniConfig.socialuniSecretKey
    }
    config.header[SocialuniRequestHeaderName.system] = socialuniSystemModule.system
    config.header[SocialuniRequestHeaderName.platform] = socialuniSystemModule.platform
    config.header[SocialuniRequestHeaderName.provider] = socialuniSystemModule.provider
    return config
  }

  override responseSuccess: (response: Response) => any = (response: Response) => {
    return response.data
  }

  override responseFail: (response: Response) => Response = (response: Response) => {
    super.responseFail(response)
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
            // UserService.clearUserInfo()
            // UserMsgUtil.unLoginMessage()
            break
          case ErrorConst.banned:
            // 理论上不需要，因为token不会失效，也不会错误
            // 已知可能，切换环境导致token不同
            // UserService.clearUserInfo()
            // AlertUtil.hint(errorMsg)
            break
          case ErrorConst.custom:
            break
          default:
            // AlertUtil.hint(errorMsg)
            // SocialuniAppAPI.sendErrorLogAPI(response.config.url, errorMsg)
            break
        }
      } else {
        // MsgUtil.systemErrorMsg()
        // SocialuniAppAPI.sendErrorLogAPI(response.config.url, result)
      }
      // 返回接口返回的错误信息
      return result
    }
    // MsgUtil.systemErrorMsg()
    // SocialuniAppAPI.sendErrorLogAPI(response.config.url, JsonUtil.toJson(response))
    return response
  }
}
