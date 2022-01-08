import PagePath from '../const/PagePath'
import { socialSystemModule, socialUserModule } from '../store'
import MsgUtil from './MsgUtil'
import AlertUtil from './AlertUtil'
import RouterUtil from './RouterUtil'
import CenterUserDetailRO from '../model/social/CenterUserDetailRO'
import SocialuniAuthQO from '../model/openData/SocialuniAuthQO'


export default class PageUtil {
  static goHome (): void {
    PageUtil.toTalkPage()
  }

  static toOAuthPage () {
    RouterUtil.navigateTo(PagePath.oAuth)
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
      if (socialUserModule.user) {
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
    const user: CenterUserDetailRO = socialUserModule.user
    if (user && user.phoneNum) {
      RouterUtil.navigateTo(PagePath.talkAdd)
    } else {
      MsgUtil.unBindPhoneNum()
    }
  }

  static async toPhonePage () {
    RouterUtil.navigateTo(PagePath.userPhone)
  }

  static toTalkPage () {
    RouterUtil.switchTab(PagePath.talk)
  }

  static toIdentityAuthPage () {
    RouterUtil.navigateTo(PagePath.identityAuth)
  }

  static toUserMatchPage (user: CenterUserDetailRO) {
    // store.commit('match/setUser', user)
    // RouterUtil.navigateTo(PagePath.userMatch)
  }

  static toMessagePage () {
    RouterUtil.navigateTo(PagePath.message)
  }

  static toFaceValuePage () {
    AlertUtil.confirm('是否查看颜值分介绍').then(() => {
      RouterUtil.navigateTo(PagePath.faceValueInfo)
    })
  }

  static toUserContactInfoPage () {
    RouterUtil.navigateTo(PagePath.userContactInfo)
  }

  static toSocialUniAuth (authVO: SocialuniAuthQO) {
    let appId = 'wx0bf5fe9ceeab514c'
    if (socialSystemModule.isMpQQ) {
      appId = '1109985787'
    }
    uni.navigateToMiniProgram({
      appId: appId,
      // envVersion: 'trial',
      // appId: '1109985787',
      path: 'pages/oAuth/oAuth',
      extraData: authVO
    })
  }
}
