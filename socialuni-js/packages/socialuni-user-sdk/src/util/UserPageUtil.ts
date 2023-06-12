import UserPagePath from "../constant/UserPagePath";
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";

export default class UserPageUtil {
    static toUserDetail(userId: string) {
        if (SocialuniAppUtil.RouterUtil.getCurrentPageURI() !== UserPagePath.userDetail) {
            SocialuniAppUtil.RouterUtil.navigateTo(UserPagePath.userDetail + '?userId=' + userId)
        }
    }

    static goBackOrMine(): void {
        const pages = getCurrentPages()
        if (pages.length === 1) {
            UserPageUtil.toMinePage()
        } else {
            SocialuniAppUtil.RouterUtil.goBack()
        }
        // uni.navigateBack({ delta: 1 })
    }

    static toEditMineInfo() {
        SocialuniAppUtil.RouterUtil.navigateTo(UserPagePath.editMineInfo)
    }

    static toUserFollowPage(followType: string) {
        SocialuniAppUtil.RouterUtil.navigateTo(UserPagePath.userFollow + '?followType=' + followType)
    }

    static toUserImgList(userId: string) {
        SocialuniAppUtil.RouterUtil.navigateTo(UserPagePath.userImgList + '?userId=' + userId)
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
        SocialuniAppUtil.RouterUtil.switchTab(UserPagePath.userMine)
    }



    static async toPhonePage() {
        SocialuniAppUtil.RouterUtil.navigateTo(UserPagePath.userPhone)
    }


    static toIdentityAuthPage() {
        SocialuniAppUtil.RouterUtil.navigateTo(UserPagePath.identityAuth)
    }

    static toUserMatchPage(user: CenterUserDetailRO) {
        // store.commit('match/setUser', user)
        // RouterUtil.navigateTo(UserPagePath.userMatch)
    }


    static toUserContactInfoPage() {
        SocialuniAppUtil.RouterUtil.navigateTo(UserPagePath.userContactInfo)
    }
}
