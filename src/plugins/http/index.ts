import Request, { requestConfig } from './request'
import TokenUtil from '@/utils/TokenUtil'
import UniUtil from '@/utils/UniUtil'
import { configModule } from '@/plugins/store'
import Alert from '@/utils/Alert'
import UserStoreCom from '@/plugins/store/UserStoreCom'
import ErrorConst from '@/const/ErrorConst'
import MsgUtil from '@/utils/MsgUtil'

const http: Request = new Request()
http.setConfig(config => { /* 设置全局配置 */
  config.baseUrl = process.env.VUE_APP_BASE /* 根域名不同 */
  config.timeout = 5 * 1000
  return config
})
http.interceptor.request((config: requestConfig) => { /* 请求之前拦截器 */
  const token = TokenUtil.get()
  if (token) {
    config.header.token = token
  } else {
    config.header.token = null
  }
  config.header.secretKey = process.env.VUE_APP_SOCAIL_SECRETKEY

  /* else {
    //如果未登录，只允许查询talk，其他全部提示要登录
    const configUrl: string = config.url
    if (configUrl.indexOf('queryTalks') < 0 && configUrl.indexOf('wxLogin') < 0) {
      BalaBala.unLoginMessage()
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
      switch (error.statusCode) {
        case ErrorConst.not_logged:
        case ErrorConst.banned:
          // 理论上不需要，因为token不会失效，也不会错误
          // 已知可能，切换环境导致token不同
          UserStoreCom.clearUserInfoCom()
          if (result && result.errorMsg) {
            Alert.error(result.errorMsg)
          }

          if (result && result.errorMsg) {
            Alert.error(result.errorMsg)
          } else {
            if (ErrorConst.not_logged === error.statusCode) {
              MsgUtil.unLoginMessage()
            } else {
              const msg: string = configModule.systemError605
              Alert.error(msg)
            }
          }
          break
        case ErrorConst.custom:
          break
        default:
          if (result && result.errorMsg) {
            Alert.error(result.errorMsg)
          } else {
            MsgUtil.systemErrorMsg()
          }
          break
      }
      return result // 返回接口返回的错误信息
    }
    MsgUtil.systemErrorMsg()
    return error
  }
)
export default http
