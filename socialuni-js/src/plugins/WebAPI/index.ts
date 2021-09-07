import Request from '@/socialuni/http/request'
import TokenUtil from '@/utils/TokenUtil'
import ErrorCode from '@/const/ErrorCode'
import UniUtil from '@/utils/UniUtil'
import CommonUtil from '@/utils/CommonUtil'
import { configModule, userModule } from '@/store'
import MsgUtil from '@/utils/MsgUtil'
import UserService from '@/service/UserService'
import Alert from '@/utils/Alert'
import ErrorConst from '@/const/ErrorConst'
import Toast from '@/utils/Toast'
import AppUtilAPI from '@/api/AppUtilAPI'


const WebAPI: Request = new Request()
WebAPI.setConfig(config => { /* 设置全局配置 */
  return config
})
WebAPI.interceptor.request(config => { /* 请求之前拦截器 */
  return config
})

WebAPI.interceptor.response(
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
export default WebAPI
