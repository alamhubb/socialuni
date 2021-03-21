import Request from '@/plugins/http/request'
import TokenUtil from '@/utils/TokenUtil'
import ErrorCode from '@/const/ErrorCode'
import UniUtil from '@/plugins/uni/UniUtil'
import CommonUtil from '@/utils/CommonUtil'
import { configModule, userModule } from '@/plugins/store'
import MsgUtil from '@/utils/MsgUtil'
import UserService from '@/service/UserService'
import Alert from '@/utils/Alert'


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
      switch (error.statusCode) {
        case ErrorCode.not_logged:
          // 理论上不需要，因为token不会失效，也不会错误
          // 已知可能，切换环境导致token不同
          UserService.clearUserInfoCom()
          MsgUtil.unLoginMessage()
          break
        case ErrorCode.banned:
          UserService.clearUserInfoCom()
          if (result) {
            Alert.error(result.errorMsg)
          } else {
            const msg: string = configModule.systemError605
            Alert.error(msg)
          }
          break
        case ErrorCode.custom:
          break
        default:
          if (result) {
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
export default WebAPI
