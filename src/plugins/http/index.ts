import Request, { requestConfig } from './request'
import TokenUtil from '@/utils/TokenUtil'
import UniUtil from '@/plugins/uni/UniUtil'
import { configModule, systemModule } from '@/store'
import UserService from '@/service/UserService'
import ErrorConst from '@/const/ErrorConst'
import MsgUtil from '@/utils/MsgUtil'
import AppUtilAPI from '@/api/AppUtilAPI'
import Alert from '@/utils/Alert'
import SocialConfig from '@/config/SocialConfig'

const http: Request = new Request()
http.setConfig(config => { /* 设置全局配置 */
  config.baseUrl = process.env.VUE_APP_BASE /* 根域名不同 */
  config.timeout = 60 * 1000
  return config
})
http.interceptor.request((config: requestConfig) => { /* 请求之前拦截器 */
  const token = TokenUtil.get()
  if (token) {
    config.header.token = token
  } else {
    config.header.token = null
  }
  config.header.secretKey = SocialConfig.socialSecretKey
  config.header.provider = systemModule.provider
  config.header.platform = systemModule.platform

  /* else {
    //如果未登录，只允许查询talk，其他全部提示要登录
    const configUrl: string = config.url
    if (configUrl.indexOf('queryTalks') < 0 && configUrl.indexOf('wxLogin') < 0) {
      MsgUtil.unLoginMessage()
      cancel('用户未登录请求了没有权限的接口', config)
    }
  } */
  return config
})

http.interceptor.response(
  response => {
    return response.data
  },
  error => {
    UniUtil.hideLoading()
    if (error.statusCode) {
      const result = error.data
      let errorMsg = ''
      switch (error.statusCode) {
        case ErrorConst.not_logged:
        case ErrorConst.banned:
          // 理论上不需要，因为token不会失效，也不会错误
          // 已知可能，切换环境导致token不同
          UserService.clearUserInfoCom()
          if (result && result.errorMsg) {
            Alert.hint(result.errorMsg)
          } else {
            if (ErrorConst.not_logged === error.statusCode) {
              MsgUtil.unLoginMessage()
            } else {
              const msg: string = configModule.systemError605
              Alert.hint(msg)
            }
          }
          break
        case ErrorConst.custom:
          break
        default:
          if (result && result.errorMsg) {
            errorMsg = result.errorMsg
            Alert.hint(errorMsg)
          } else {
            MsgUtil.systemErrorMsg()
          }
          AppUtilAPI.sendErrorLogAPI(error.config.url, errorMsg)
          break
      }
      return result // 返回接口返回的错误信息
    }
    MsgUtil.systemErrorMsg()
    return error
  }
)
export default http
