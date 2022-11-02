import HttpRequest, {requestConfig} from '@/socialuni/plugins/http/httpRequest'
import TokenUtil from '../../utils/TokenUtil'
import UniUtil from '../../utils/UniUtil'
import {socialConfigModule, socialSystemModule} from '../../store'
import ErrorConst from '../../constant/ErrorConst'
import MsgUtil from '../../utils/MsgUtil'
import SocialuniAppAPI from '../../api/socialuni/SocialuniAppAPI'
import AlertUtil from '../../utils/AlertUtil'
import XmlUtil from '@/socialuni/utils/XmlUtil'
import XmlResultRO from '@/socialuni/model/base/XmlResultRO'
import ObjectUtil from '@/socialuni/utils/ObjectUtil'
import UserService from '@/socialuni/service/UserService'

const request: HttpRequest = new HttpRequest()

const socialHttpUrl = process.env.VUE_APP_BASE_URL

request.setConfig(config => { /* 设置全局配置 */
  config.baseUrl = socialHttpUrl + '/'/* 根域名不同 */
  config.timeout = 10 * 1000
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
  // config.header.socialuniSecretKey = 'f12be0da13bf4cf9b672c5e7957c71f2'
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
            // 理论上不需要，因为token不会失效，也不会错误
            // 已知可能，切换环境导致token不同
            UserService.clearUserInfoCom()
            MsgUtil.unLoginMessage()
            break
          case ErrorConst.banned:
            // 理论上不需要，因为token不会失效，也不会错误
            // 已知可能，切换环境导致token不同
            UserService.clearUserInfoCom()
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
        if (typeof result === 'string') {
          //之前存在返回xml的情况
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
              const msg: string = socialConfigModule.appMoreConfig.errorMsg604SystemError
              AlertUtil.hint(resultRO.Message + '，请重试，' + msg)
              // 返回接口返回的错误信息
              return result
            } finally {
            }
          }
        }
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
