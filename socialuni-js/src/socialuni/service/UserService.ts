import { socialChatModule, socialNotifyModule, socialUserModule } from '../store'
import WebsocketUtil from '../utils/WebsocketUtil'
import TokenUtil from '../utils/TokenUtil'
import SocialLoginRO from '../model/social/SocialLoginRO'
import CenterUserDetailRO from '../model/social/CenterUserDetailRO'

export default class UserService {
  //清空用户信息的组合操作
  static clearUserInfoCom () {
    TokenUtil.remove()
    socialUserModule.removeUser()
    WebsocketUtil.websocketClose()
    chatModule.getChatsAction()
  }

  //获取通知列表
  //连接websocket
  /**
   * 调用后台仅user和user初始化相关信息,通知列表，开启websocket连接
   */
  static getMineUserInitDataActionByToken (loginRO: SocialLoginRO<CenterUserDetailRO>) {
    TokenUtil.set(loginRO.token)
    //登录之后重连websocket
    WebsocketUtil.websocketClose()
    socialUserModule.setUser(loginRO.user)
    socialNotifyModule.queryNotifiesAction()
    socialChatModule.getChatsAction()
    // appModule.getImgPathAction()
    return loginRO.user
  }

  static getMineUserInitDataAction () {
    if (TokenUtil.hasToken()) {
      socialUserModule.getMineUserAction()
    }
  }
}
