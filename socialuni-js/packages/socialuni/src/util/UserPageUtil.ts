import RouterUtil from "socialuni-util/src/util/RouterUtil";
import UserPagePath from "../constant/UserPagePath";
import CenterUserDetailRO from "socialuni/src/model/social/CenterUserDetailRO";

export default class UserPageUtil {
    static toUserDetail(userId: string) {
        if (RouterUtil.getCurrentPageURI() !== UserPagePath.userDetail) {
            RouterUtil.navigateTo(UserPagePath.userDetail + '?userId=' + userId)
        }
    }

    static goBackOrMine(): void {
        const pages = getCurrentPages()
        if (pages.length === 1) {
            UserPageUtil.toMinePage()
        } else {
            RouterUtil.goBack()
        }
        // uni.navigateBack({ delta: 1 })
    }

    static toEditMineInfo() {
        RouterUtil.navigateTo(UserPagePath.editMineInfo)
    }

    static toUserFollowPage(followType: string) {
        RouterUtil.navigateTo(UserPagePath.userFollow + '?followType=' + followType)
    }

    static toUserImgList(userId: string) {
        RouterUtil.navigateTo(UserPagePath.userImgList + '?userId=' + userId)
    }

    static toVipPage() {
        /*constant isIos: boolean = systemModule.isIos
        if (isIos) {
          // 由于相关规范，iOS功能暂不可用
          MsgUtil.iosDisablePay()
        } else {
          constant user: UserVO = userModule.user
          if (user) {
            RouterUtil.navigateTo(UserPagePath.userVip)
          } else {
            MsgUtil.unLoginMessage()
          }
        }*/
    }

    static toMinePage() {
        RouterUtil.switchTab(UserPagePath.userMine)
    }



    static async toPhonePage() {
        RouterUtil.navigateTo(UserPagePath.userPhone)
    }


    static toIdentityAuthPage() {
        RouterUtil.navigateTo(UserPagePath.identityAuth)
    }

    static toUserMatchPage(user: CenterUserDetailRO) {
        // store.commit('match/setUser', user)
        // RouterUtil.navigateTo(UserPagePath.userMatch)
    }


    static toUserContactInfoPage() {
        RouterUtil.navigateTo(UserPagePath.userContactInfo)
    }
}
