import {notifyModule, platformModule, talkModule, userModule} from './index'
import WebsocketUtil from '@/utils/WebsocketUtil'
import TokenUtil from '@/utils/TokenUtil'
import AppInitDataVO from "@/model/common/AppInitDataVO";
import ResultVO from "@/model/ResultVO";
import TalkFilterUtil from "@/utils/TalkFilterUtil";

export default class UserStoreCom {
  //清空用户信息的组合操作
  static clearUserInfoCom() {
    TokenUtil.remove()
    userModule.setUser(null)
    WebsocketUtil.websocketClose()
  }

  //获取通知列表
  //连接websocket
  /**
   * 调用后台仅user和user初始化相关信息,通知列表，开启websocket连接
   */
  static getMineUserInitDataActionByToken(token: string) {
    TokenUtil.set(token)
    //登陆之后重连websocket
    WebsocketUtil.websocketClose()
    return this.getMineUserInitDataAction()
  }

  static getMineUserInitDataAction() {
    return userModule.getMineUserAction().then(() => {
      //查询通知列表
      notifyModule.queryNotifiesAction()
    })
  }

  static initUserStore(res: ResultVO<AppInitDataVO>) {
    const user = res.data.user
    // 如果存在用户
    if (user) {
      //首次登陆且用户有年龄,默认设置筛选上下5岁的用户
      if (!TalkFilterUtil.getNotFirstSetAge() && user.age) {
        const minAge = user.age - 5
        const maxAge = user.age + 5
        talkModule.userMinAge = minAge
        talkModule.userMaxAge = maxAge
        TalkFilterUtil.setFistSetUserAge(minAge, maxAge)
      }
      notifyModule.notifies = res.data.notifies
      userModule.setUser(res.data.user)
      // 所有操作都是登陆后才可以操作的
      platformModule.qq_talkTemplateId = res.data.qq_talkTemplateId
      platformModule.qq_commentTemplateId = res.data.qq_commentTemplateId
      platformModule.qq_reportResultTemplateId = res.data.qq_reportResultTemplateId
      platformModule.qq_violationTemplateId = res.data.qq_violationTemplateId

      platformModule.wx_talkTemplateId = res.data.wx_talkTemplateId
      platformModule.wx_commentTemplateId = res.data.wx_commentTemplateId
      platformModule.wx_reportResultTemplateId = res.data.wx_reportResultTemplateId
      platformModule.wx_violationTemplateId = res.data.wx_violationTemplateId
    }
    // chatModule.setChats(res.data.chats)
  }
}
