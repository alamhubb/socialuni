import { notifyModule, platformModule, talkModule, userModule } from './index'
import WebsocketUtil from '@/utils/WebsocketUtil'
import TokenUtil from '@/utils/TokenUtil'
import AppInitDataVO from '@/model/common/AppInitDataVO'
import ResultVO from '@/model/ResultVO'
import TalkFilterUtil from '@/utils/TalkFilterUtil'
import LoginResultVO from '@/model/common/LoginResultVO'

export default class UserStoreCom {
  //清空用户信息的组合操作
  static clearUserInfoCom () {
    TokenUtil.remove()
    userModule.setUser(null)
    WebsocketUtil.websocketClose()
  }

  //获取通知列表
  //连接websocket
  /**
   * 调用后台仅user和user初始化相关信息,通知列表，开启websocket连接
   */
  static getMineUserInitDataActionByToken (res: ResultVO<LoginResultVO>) {
    TokenUtil.set(res.data.tokenCode)
    //登录之后重连websocket
    WebsocketUtil.websocketClose()
    userModule.setUser(res.data.user)
    return res.data.user
  }

  static getMineUserInitDataAction () {
    return userModule.getMineUserAction().then(() => {
      //查询通知列表
      notifyModule.queryNotifiesAction()
    })
  }
}
