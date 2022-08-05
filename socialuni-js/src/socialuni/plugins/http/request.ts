import HttpRequest, { requestConfig } from '@/socialuni/plugins/http/httpRequest'
import TokenUtil from '../../utils/TokenUtil'
import UniUtil from '../../utils/UniUtil'
import { socialConfigModule, socialSystemModule } from '../../store'
import UserService from '../../service/UserService'
import ErrorConst from '../../constant/ErrorConst'
import MsgUtil from '../../utils/MsgUtil'
import AppUtilAPI from '../../api/AppUtilAPI'
import AlertUtil from '../../utils/AlertUtil'
import { socialConfig } from '../../index'
import SocialuniConfig from '../../config/SocialuniConfig'
import SocialSystemInfo from '../../constant/SocialSystemInfo'
import XmlUtil from '@/socialuni/utils/XmlUtil'
import XmlResultRO from '@/socialuni/model/base/XmlResultRO'
import ObjectUtil from '@/socialuni/utils/ObjectUtil'

const request: HttpRequest = new HttpRequest()

let socialHttpUrl = process.env.VUE_APP_SOCIALUNI_URL
if (!socialHttpUrl) {
  if (SocialSystemInfo.isDevMode) {
    socialHttpUrl = SocialuniConfig.devSocialuniHttpBaseUrl
  } else {
    socialHttpUrl = SocialuniConfig.socialuniHttpBaseUrl
  }
}

request.setConfig(config => { /* 设置全局配置 */
  config.baseUrl = socialHttpUrl + '/'/* 根域名不同 */
  config.timeout = 60 * 1000
  return config
})
request.interceptor.request((config: requestConfig) => { /* 请求之前拦截器 */
  const token = TokenUtil.get()
  if (token) {
    config.header.token = token
    //下次尝试把这里删除
  } else {
    config.header.token = null
  }
  config.header.secretKey = socialConfig.secretKey
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
          case ErrorConst.banned:
            // 理论上不需要，因为token不会失效，也不会错误
            // 已知可能，切换环境导致token不同
            // UserService.clearUserInfoCom()
            AlertUtil.hint(errorMsg)
            break
          case ErrorConst.custom:
            break
          default:
            AlertUtil.hint(errorMsg)
            AppUtilAPI.sendErrorLogAPI(error.config.url, errorMsg)
            break
        }
      } else {
        if (typeof result === 'string') {
          if (result.startsWith('<?xml')) {
            try {
              const errorXml = XmlUtil.parseXml(result)
              const resultXml = errorXml.elements[0]
              const resultRO: XmlResultRO = {} as XmlResultRO
              for (const element of resultXml.elements) {
                if (element.elements) {
                  resultRO[element.name] = element.elements[0].text
                }
              }
              const msg: string = socialConfigModule.systemError604
              AlertUtil.hint(resultRO.Message + '，请重试，' + msg)
              // 返回接口返回的错误信息
              return result
            } finally {
            }
          }
        }
        MsgUtil.systemErrorMsg()
        AppUtilAPI.sendErrorLogAPI(error.config.url, result)
      }
      // 返回接口返回的错误信息
      return result
    }
    MsgUtil.systemErrorMsg()
    AppUtilAPI.sendErrorLogAPI(error.config.url, ObjectUtil.toJson(error))
    return error
  }
)
export default request
