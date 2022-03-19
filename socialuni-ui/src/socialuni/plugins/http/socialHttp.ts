import HttpRequest, { requestConfig } from './httpRequest'
import TokenUtil from '../../utils/TokenUtil'
import UniUtil from '../../utils/UniUtil'
import UserService from '../../service/UserService'
import ErrorConst from '../../const/ErrorConst'
import MsgUtil from '../../utils/MsgUtil'
import AppUtilAPI from '../../api/AppUtilAPI'
import AlertUtil from '../../utils/AlertUtil'
import { socialConfig } from '../../index'
import SocialuniConfig from '../../config/SocialuniConfig'
import SocialSystemInfo from '../../const/SocialSystemInfo'

const socialHttp: HttpRequest = new HttpRequest()

let socialHttpUrl = import.meta.env.VITE_APP_BASE_URL
console.log(socialHttpUrl)
if (!socialHttpUrl) {
  if (SocialSystemInfo.isDevMode) {
    socialHttpUrl = SocialuniConfig.devSocialuniHttpBaseUrl
  } else {
    socialHttpUrl = SocialuniConfig.socialuniHttpBaseUrl
  }
}

socialHttp.setConfig(config => { /* 设置全局配置 */
  config.baseUrl = socialHttpUrl + '/'/* 根域名不同 */
  config.timeout = 60 * 1000
  return config
})
socialHttp.interceptor.request((config: requestConfig) => { /* 请求之前拦截器 */
  const token = TokenUtil.get()
  if (token) {
    config.header.token = token
    //下次尝试把这里删除
  } else {
    config.header.token = null
  }
  console.log(process)
  console.log(process.env)
  config.header.secretKey = socialConfig.secretKey
  // config.header.secretKey = import.meta.env.VITE_APP_SOCIALUNI_SECRETKEY
  // config.header.provider = socialSystemModule.mpPlatform
  // config.header.platform = socialSystemModule.platform
  // config.header.system = socialSystemModule.system

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
            AlertUtil.hint(result.errorMsg)
          } else {
            if (ErrorConst.not_logged === error.statusCode) {
              MsgUtil.unLoginMessage()
            } else {
              const msg: string = socialConfigModule.systemError605
              AlertUtil.hint(msg)
            }
          }
          break
        case ErrorConst.custom:
          break
        default:
          if (result && result.errorMsg) {
            errorMsg = result.errorMsg
            AlertUtil.hint(errorMsg)
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
