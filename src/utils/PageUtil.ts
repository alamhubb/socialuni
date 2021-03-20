import PagePath from '../const/PagePath'
import store, { systemModule, userModule } from '@/plugins/store'
import MsgUtil from '@/utils/MsgUtil'
import Alert from './Alert'
import RouterUtil from '@/utils/RouterUtil'
import UserVO from '@/model/user/UserVO'

export default class PageUtil {
  static goHome (): void {
    PageUtil.toTalkPage()
  }

  static toVipPage () {
    /*const isIos: boolean = systemModule.isIos
    if (isIos) {
      // 由于相关规范，iOS功能暂不可用
      MsgUtil.iosDisablePay()
    } else {
      const user: UserVO = userModule.user
      if (user) {
        RouterUtil.navigateTo(PagePath.userVip)
      } else {
        MsgUtil.unLoginMessage()
      }
    }*/
  }

  static toShellPage () {
    if (systemModule.isIos) {
      // 由于相关规范，iOS功能暂不可用
      MsgUtil.iosDisablePay()
    } else {
      if (userModule.user) {
        RouterUtil.navigateTo(PagePath.userShell)
      } else {
        MsgUtil.unLoginMessage()
      }
    }
  }

  static toLoveValuePage () {
    RouterUtil.navigateTo(PagePath.loveValue)
  }

  static toMinePage () {
    RouterUtil.switchTab(PagePath.userMine)
  }

  static toTalkAddPage () {
    const user: UserVO = userModule.user
    if (user && user.phoneNum) {
      RouterUtil.navigateTo(PagePath.talkAdd)
    } else {
      MsgUtil.unBindPhoneNum()
    }
  }

  static toPhonePage () {
    RouterUtil.navigateTo(PagePath.userPhone)
  }

  static toTalkPage () {
    RouterUtil.switchTab(PagePath.talk)
  }

  static toIdentityAuthPage () {
    RouterUtil.navigateTo(PagePath.identityAuth)
  }

  static toUserMatchPage (user: UserVO) {
    store.commit('match/setUser', user)
    // RouterUtil.navigateTo(PagePath.userMatch)
  }

  static toMessagePage () {
    RouterUtil.navigateTo(PagePath.message)
  }

  static toFaceValuePage () {
    Alert.confirm('是否查看颜值分介绍').then(() => {
      RouterUtil.navigateTo(PagePath.faceValueInfo)
    })
  }

  static toUserContactInfoPage () {
    RouterUtil.navigateTo(PagePath.userContactInfo)
  }
}
