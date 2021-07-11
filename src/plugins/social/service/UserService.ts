import WebsocketUtil from '@/utils/WebsocketUtil'
import TokenUtil from '@/utils/TokenUtil'
import ResultVO from '@/model/ResultVO'
import UniUtil from '@/plugins/social/utils/UniUtil'
import ErrorCode from '@/const/ErrorCode'
import Toast from '@/utils/Toast'
import SocialUserLoginRO from '@/model/socialuni/SocialUserLoginRO'
import UniProviderLoginQO from '@/plugins/uniapp-sdk/model/UniProviderLoginQO'
import { userModule } from '@/store'

export default class UserService {
  //清空用户信息的组合操作
  static clearUserInfoCom () {
    TokenUtil.remove()
    userModule.removeUser()
    WebsocketUtil.websocketClose()
  }

  //获取通知列表
  //连接websocket
  /**
   * 调用后台仅user和user初始化相关信息,通知列表，开启websocket连接
   */
  static getMineUserInitDataActionByToken (res: ResultVO<SocialUserLoginRO>) {
    TokenUtil.set(res.data.token)
    //登录之后重连websocket
    // WebsocketUtil.websocketClose()
    userModule.setUser(res.data.user)
    // notifyModule.queryNotifiesAction()
    // chatModule.getChatsAction()
    return res.data.user
  }

  static getMineUserInitDataAction () {
    if (TokenUtil.hasToken()) {
      userModule.getMineUserAction()
    }
  }


  //微信绑定手机号使用
  static getWxPhoneNumberByLogin (obj: any) {
    /*LoginService.getLoginData(systemModule.provider as Provider).then((loginData: ProviderUserVO) => {
      Object.assign(loginData, obj.detail)
      // 代表已过期
      loginData.sessionEnable = false
      /!*this.getPhoneNumberAfterHandler(loginData).finally(() => {
        this.phoneBtnDisabled = false
      })*!/
    })*/
  }

  static async getPhoneNumberByWx (obj: any) {
    if (obj.detail.errMsg === 'getPhoneNumber:ok') {
      // 默认未过期
      await UniUtil.checkSession().catch(() => {
        UserService.getWxPhoneNumberByLogin(obj)
      })
      const loginData: UniProviderLoginQO = new UniProviderLoginQO()
      Object.assign(loginData, obj.detail)
      // loginData.sessionEnable = true
      //前台获取为未过期，但也有可能已过期，尝试调用后台获取，确认是否未过期
      try {
        return await userModule.bindPhoneNumAction(loginData)
      } catch (error) {
        //如果为自定义，则将过期标示改为已过期调用后台
        if (error.errorCode === ErrorCode.custom) {
          Toast.toast(error.data)
          UserService.getWxPhoneNumberByLogin(obj)
          throw error
        }
      }
    } else {
      Toast.toast('您选择了不绑定')
      throw Error('您选择了不绑定')
    }
  }
}
