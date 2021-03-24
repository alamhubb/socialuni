import Request, { requestConfig } from './request'
import TokenUtil from '@/utils/TokenUtil'
import UniUtil from '@/plugins/uni/UniUtil'
import { appModule, configModule } from '@/plugins/store'
import Alert from '@/utils/Alert'
import UserService from '@/service/UserService'
import ErrorConst from '@/const/ErrorConst'
import MsgUtil from '@/utils/MsgUtil'
import AppUtilAPI from '@/api/AppUtilAPI'
import Toast from '@/utils/Toast'

const socialHttp: Request = new Request()
socialHttp.setConfig(config => { /* 设置全局配置 */
  config.baseUrl = process.env.VUE_APP_SOCAIL_ROOT_URL /* 根域名不同 */
  config.timeout = 60 * 1000
  return config
})
socialHttp.interceptor.request((config: requestConfig) => { /* 请求之前拦截器 */
  const token = TokenUtil.get()
  if (token) {
    config.header.token = token
  } else {
    config.header.token = null
  }
  config.header.secretKey = appModule.appSocialSecretKey

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

socialHttp.interceptor.response(
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
            Toast.toastLong(result.errorMsg)
          }

          if (result && result.errorMsg) {
            Toast.toastLong(result.errorMsg)
          } else {
            if (ErrorConst.not_logged === error.statusCode) {
              MsgUtil.unLoginMessage()
            } else {
              const msg: string = configModule.systemError605
              Toast.toastLong(msg)
            }
          }
          break
        case ErrorConst.custom:
          break
        default:
          if (result && result.errorMsg) {
            errorMsg = result.errorMsg
            Toast.toastLong(errorMsg)
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
export default socialHttp
