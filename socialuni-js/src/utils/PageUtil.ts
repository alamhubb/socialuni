import PagePath from '../const/PagePath'
import store, { socialSystemModule, userModule } from '@/store'
import MsgUtil from '@/utils/MsgUtil'
import Alert from './Alert'
import RouterUtil from '@/utils/RouterUtil'
import CenterUserDetailRO from '../model/social/CenterUserDetailRO'
import SocialUniAuthVO from '@/model/openData/SocialUniAuthVO'


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
    if (socialSystemModule.isIos) {
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
    const user: CenterUserDetailRO = userModule.user
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

  static toUserMatchPage (user: CenterUserDetailRO) {
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

  static toSocialUniAuth (authVO: SocialUniAuthVO) {
    let appId = 'wx0bf5fe9ceeab514c'
    if (socialSystemModule.isMpQQ) {
      appId = '1109985787'
    }
    uni.navigateToMiniProgram({
      appId: appId,
      // appId: '1109985787',
      path: 'pages/user/login',
      extraData: authVO
    })
  }
}
