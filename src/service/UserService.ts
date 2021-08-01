import { chatModule, notifyModule, userModule } from '../store'
import WebsocketUtil from '@/utils/WebsocketUtil'
import TokenUtil from '@/utils/TokenUtil'
import SocialLoginRO from '@/model/social/SocialLoginRO'
import UserVO from '@/model/user/UserVO'

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
  static getMineUserInitDataActionByToken (loginRO: SocialLoginRO<UserVO>) {
    TokenUtil.set(loginRO.token)
    //登录之后重连websocket
    WebsocketUtil.websocketClose()
    userModule.setUser(loginRO.user)
    notifyModule.queryNotifiesAction()
    chatModule.getChatsAction()
    // appModule.getImgPathAction()
    return loginRO.user
  }

  static getMineUserInitDataAction () {
    if (TokenUtil.hasToken()) {
      userModule.getMineUserAction()
    }
  }
}
