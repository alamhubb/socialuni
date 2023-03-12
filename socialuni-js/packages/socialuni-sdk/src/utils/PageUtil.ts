import MsgUtil from './MsgUtil'
import AlertUtil from 'socialuni-sdk/src/utils/AlertUtil'
import RouterUtil from 'socialuni-sdk/src/utils/RouterUtil'
import PagePath from "socialuni-constant/constant/PagePath";
import {socialSystemModule} from "socialuni-sdk/src/store/store";
import {socialConfigModule} from "socialuni-sdk/src/store/store";
import {socialUserModule} from "socialuni-sdk/src/store/store";
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";
import SocialuniAuthQO from "socialuni-api/src/model/openData/SocialuniAuthQO";
import SkipType from "socialuni-constant/constant/SkipType";
import SkipUrlConst from "socialuni-constant/constant/SkipUrlConst";
import PlatformUtils from "./PlatformUtils";
import SocialuniMineUserRO from "socialuni-api/src/model/user/SocialuniMineUserRO";


export default class PageUtil {
    static goHome(): void {
        PageUtil.toTalkPage()
    }

    static toTalkDetail(talkId: string) {
        RouterUtil.navigateTo(PagePath.talkDetail + '?talkId=' + talkId)
    }


    static toUserDetail(userId: string) {
        RouterUtil.navigateTo(PagePath.userDetail + '?userId=' + userId)
    }

    static toEditMineInfo() {
        RouterUtil.navigateTo(PagePath.editMineInfo)
    }

    static toUserFollowPage(followType: string) {
        RouterUtil.navigateTo(PagePath.userFollow + '?followType=' + followType)
    }

    static toUserImgList(userId: string) {
        RouterUtil.navigateTo(PagePath.userImgList + '?userId=' + userId)
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

    static toCoinPage() {
        if (socialSystemModule.isProd && socialSystemModule.isIos) {
            // 由于相关规范，iOS功能暂不可用
            MsgUtil.iosDisablePay()
        } else {
            if (socialUserModule.mineUser) {
                RouterUtil.navigateTo(PagePath.coin)
            } else {
                MsgUtil.unLoginMessage()
            }
        }
    }

    static toCoinRecordPage(pageType: string) {
        PlatformUtils.checkPay()
        if (socialUserModule.mineUser) {
            RouterUtil.navigateTo(PagePath.coinRecord + '?pageType=' + pageType)
        } else {
            MsgUtil.unLoginMessage()
        }
    }

    static toLoveValuePage() {
        RouterUtil.navigateTo(PagePath.loveValue)
    }

    static toChatFriend() {
        RouterUtil.navigateTo(PagePath.friend)
    }

    static toFriendApply() {
        RouterUtil.navigateTo(PagePath.friendApply)
    }

    static toMinePage() {
        RouterUtil.switchTab(PagePath.userMine)
    }

    static toTalkAddPage() {
        const user: SocialuniMineUserRO = socialUserModule.mineUser as any
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

    static toMessagePageByUserId(userId: string) {
        RouterUtil.navigateTo(PagePath.message + '?userId=' + userId)
    }

    static toMessagePageByGroupId(groupId: string) {
        RouterUtil.navigateTo(PagePath.message + '?groupId=' + groupId)
    }

    static toIMGroupMember(groupId: string) {
        RouterUtil.navigateTo("/pages/chat/groupMember?id=" + groupId);
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
            await PageUtil.navigateToMp(appId, 'pages/oAuth/oAuth', authVO)
        } catch (e) {
            console.log(e)
        }
    }

    static getWebUrl(webUrl: string, pageTitle = ''): string {
        if (webUrl) {
            return PagePath.webBrowser + '?title=' + pageTitle + '&url=' + webUrl
        }
        return null
    }

    /**
     * 保留当前页面，跳转到应用内的某个页面，使用uni.navigateBack可以返回到原页面。
     * @param pagePath
     * @param params
     */

    /*static navigateTo(pagePath: string, params?: object): void {
        console.log(pagePath)
        if (params) {
            const paramObj = new URLSearchParams()
            for (const key in params) {
                paramObj.append(key, params[key])
            }
            pagePath = pagePath + '?' + paramObj.toString()
        }
        uni.navigateTo({url: pagePath})
    }*/

    static navigateToAll(type: string, skipUrl: string, pageTitle: string) {
        if (type === SkipType.mp) {
            PageUtil.navigateToMp(skipUrl, pageTitle)
        } else if (type === SkipType.web) {
            PageUtil.navigateToWeb(skipUrl, pageTitle)
        } else if (type === SkipType.local) {
            RouterUtil.navigateTo(skipUrl)
        } else {
            PageUtil.toWebHome()
        }
    }

    static toWebHome(): void {
        RouterUtil.navigateTo(SkipUrlConst.homeUrl())
    }


    static navigateToWeb(webUrl: string, pageTitle?: string): void {
        RouterUtil.navigateTo(PageUtil.getWebUrl(webUrl, pageTitle))
    }

    static async navigateToMp(appId: string, path: string = null, extraData: any = null) {
        return new Promise((resolve, reject) => {
            uni.navigateToMiniProgram({
                appId: appId,
                path: path,
                extraData: extraData,
                success(res) {
                    resolve(res)
                },
                fail(err) {
                    reject(err)
                }
            })
        })
    }

    /**
     * 关闭当前页面，跳转到应用内的某个页面，需要跳转的应用内非 tabBar 的页面的路径。
     * @param pagePath
     */
    static redirectTo(pagePath: string): void {
        uni.redirectTo({url: pagePath})
    }

    /**
     * 关闭所有页面，打开到应用内的某个页面。，如果跳转的页面路径是 tabBar 页面则不能带参数
     * @param pagePath
     */
    static reLaunch(pagePath: string): void {
        uni.reLaunch({url: pagePath})
    }

    /**
     * 跳转到 tabBar 页面，并关闭其他所有非 tabBar 页面，跳转到 tabBar 页面只能使用 switchTab 跳转。
     * @param pagePath
     */
    static switchTab(pagePath: string): void {
        if (socialSystemModule.isApp) {
            uni.showTabBar()
        }
        uni.switchTab({url: pagePath})
    }

    /**
     * 关闭当前页面，返回上一页面或多级页面。可通过 getCurrentPages() 获取当前的页面栈，决定需要返回几层。
     */
    static goBackOrHome(): void {
        const pages = getCurrentPages()
        if (pages.length === 1) {
            PageUtil.goHome()
        } else {
            RouterUtil.goBack()
        }
        // uni.navigateBack({ delta: 1 })
    }

    static goBackOrMine(): void {
        const pages = getCurrentPages()
        if (pages.length === 1) {
            PageUtil.toMinePage()
        } else {
            RouterUtil.goBack()
        }
        // uni.navigateBack({ delta: 1 })
    }

    static goBack(): void {
        uni.navigateBack({delta: 1})
    }

    static getCurrentPageURI(): string {
        return '/' + getCurrentPages()[getCurrentPages().length - 1].route
    }

    static getCurrentPage(): any {
        return getCurrentPages()[getCurrentPages().length - 1]
    }
}
