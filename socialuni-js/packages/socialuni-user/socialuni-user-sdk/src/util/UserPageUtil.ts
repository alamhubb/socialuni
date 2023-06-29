import UserPagePath from "../constant/UserPagePath";
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import MsgUtil from "socialuni-app-sdk/src/util/MsgUtil";
import {socialuniUserModule} from "../store/SocialuniUserModule";
import UserMsgUtil from "./UserMsgUtil";
import PlatformUtils from "./PlatformUtils";

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

    static toCoinPage() {
        if (socialuniSystemModule.isProd && socialuniSystemModule.isIos) {
            // 由于相关规范，iOS功能暂不可用
            MsgUtil.iosDisablePay()
        } else {
            if (socialuniUserModule.mineUser) {
                SocialuniAppUtil.RouterUtil.navigateTo(UserPagePath.userCoin)
            } else {
                UserMsgUtil.unLoginMessage()
            }
        }
    }

    static toCoinRecordPage(pageType: string) {
        PlatformUtils.checkPay()
        if (socialuniUserModule.mineUser) {
            SocialuniAppUtil.RouterUtil.navigateTo(UserPagePath.coinRecord + '?pageType=' + pageType)
        } else {
            UserMsgUtil.unLoginMessage()
        }
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
