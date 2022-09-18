import PagePath from '../constant/PagePath'
import {socialConfigModule, socialSystemModule, socialUserModule} from '../store'
import MsgUtil from './MsgUtil'
import AlertUtil from './AlertUtil'
import RouterUtil from './RouterUtil'
import CenterUserDetailRO from '../model/social/CenterUserDetailRO'
import SocialuniAuthQO from '../model/openData/SocialuniAuthQO'


export default class PageUtil {
  static goHome(): void {
    PageUtil.toTalkPage()
  }

  static toUserDetail(userId: string) {
    RouterUtil.navigateTo(PagePath.userDetail, {userId})
  }

  static toEditMineInfo() {
    RouterUtil.navigateTo(PagePath.editMineInfo)
  }

  static toUserImgList(userId: string) {
    RouterUtil.navigateTo(PagePath.userImgList, {userId})
  }

  static toOAuthPage() {
    RouterUtil.navigateTo(PagePath.oAuth)
  }

  static toVipPage() {
    /*constant isIos: boolean = systemModule.isIos
    if (isIos) {
      // 由于相关规范，iOS功能暂不可用
      MsgUtil.iosDisablePay()
    } else {
      constant user: UserVO = userModule.user
      if (user) {
        RouterUtil.navigateTo(PagePath.userVip)
      } else {
        MsgUtil.unLoginMessage()
      }
    }*/
  }

  static toShellPage() {
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

  static toLoveValuePage() {
    RouterUtil.navigateTo(PagePath.loveValue)
  }

  static toMinePage() {
    RouterUtil.switchTab(PagePath.userMine)
  }

  static toTalkAddPage() {
    const user: CenterUserDetailRO = socialUserModule.user
    if (!user || !user.phoneNum) {
      MsgUtil.unBindPhoneNum()
      //没设置校园，且应用类型要求必须设置
    } else if (!user.schoolName && socialConfigModule.appConfig.mustSetSchoolCanPost) {
      // 如果登录了仅仅没绑定手机号，则提示跳转，区分qq和微信不同
      AlertUtil.confirm('设置学校名称才能发表内容，是否前往设置学校名称页面')
        .then(() => {
          PageUtil.toMinePage()
        })
    } else {
      RouterUtil.navigateTo(PagePath.talkAdd)
    }
  }

  static async toPhonePage() {
    RouterUtil.navigateTo(PagePath.userPhone)
  }

  static toTalkPage() {
    RouterUtil.switchTab(PagePath.talk)
  }

  static toIdentityAuthPage() {
    RouterUtil.navigateTo(PagePath.identityAuth)
  }

  static toUserMatchPage(user: CenterUserDetailRO) {
    // store.commit('match/setUser', user)
    // RouterUtil.navigateTo(PagePath.userMatch)
  }

  static toMessagePage() {
    RouterUtil.navigateTo(PagePath.message)
  }

  static toFaceValuePage() {
    AlertUtil.confirm('是否查看颜值分介绍').then(() => {
      RouterUtil.navigateTo(PagePath.faceValueInfo)
    })
  }

  static toUserContactInfoPage() {
    RouterUtil.navigateTo(PagePath.userContactInfo)
  }

  static async toSocialUniAuth(authVO: SocialuniAuthQO) {
    let appId = 'wx0bf5fe9ceeab514c'
    if (socialSystemModule.isMpQQ) {
      appId = '1109985787'
    }
    try {
      await RouterUtil.navigateToMp(appId, 'pages/oAuth/oAuth', authVO)
    } catch (e) {
      console.log(e)
    }
  }
}
