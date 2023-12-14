import RouterUtil from "./RouterUtil";
import PagePath from "socialuni-constant/constant/PagePath";

export default class PageUtil {
    /*static goHome(): void {
        RouterUtil.switchTab(PagePath.home)
    }

    static goBackOrHome(): void {
        const pages = getCurrentPages()
        if (pages.length === 1) {
            PageUtil.goHome()
        } else {
            RouterUtil.goBack()
        }
        // uni.navigateBack({ delta: 1 })
    }

    static toOAuthPage() {
        RouterUtil.navigateTo(UserPagePath.oAuth)
    }

    static toVipPage() {
        /!*constant isIos: boolean = systemModule.isIos
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
        }*!/
    }

    static toCoinPage() {
        if (socialuniSystemModule.isProd && socialuniSystemModule.isIos) {
            // 由于相关规范，iOS功能暂不可用
            MsgUtil.iosDisablePay()
        } else {
            if (socialuniUserModule.mineUser) {
                RouterUtil.navigateTo(UserPagePath.coin)
            } else {
                MsgUtil.unLoginMessage()
            }
        }
    }

    static toCoinRecordPage(pageType: string) {
        PlatformUtils.checkPay()
        if (socialuniUserModule.mineUser) {
            RouterUtil.navigateTo(UserPagePath.coinRecord + '?pageType=' + pageType)
        } else {
            MsgUtil.unLoginMessage()
        }
    }

    static toLoveValuePage() {
        RouterUtil.navigateTo(UserPagePath.loveValue)
    }

    static toChatFriend() {
        RouterUtil.navigateTo(ImPagePath.friend)
    }

    static toFriendApply() {
        RouterUtil.navigateTo(ImPagePath.friendApply)
    }

    static toMinePage() {
        RouterUtil.switchTab(UserPagePath.userMine)
    }

    static toTalkAddPage() {
        const user: SocialuniMineUserRO = socialuniUserModule.mineUser as any
        if (!user || !user.phoneNum) {
            MsgUtil.unBindPhoneNum()
            //没设置校园，且应用类型要求必须设置
        } else if (!user.schoolName && socialuniConfigModule.appConfig.mustSetSchoolCanPost) {
            // 如果登录了仅仅没绑定手机号，则提示跳转，区分qq和微信不同
            SocialuniAppUtil.AlertUtil.confirm('设置学校名称才能发表内容，是否前往设置学校名称页面')
                .then(() => {
                    PageUtil.toMinePage()
                })
        } else {
            RouterUtil.navigateTo(UserPagePath.talkAdd)
        }
    }

    static toTalkPage() {
        RouterUtil.switchTab(PagePath.talk)
    }

    static toIdentityAuthPage() {
        RouterUtil.navigateTo(UserPagePath.identityAuth)
    }

    static toUserMatchPage(user: CenterUserDetailRO) {
        // store.commit('match/setUser', user)
        // RouterUtil.navigateTo(UserPagePath.userMatch)
    }

    static toMessagePageByUserId(userId: string) {
        RouterUtil.navigateTo(UserPagePath.message + '?userId=' + userId)
    }

    static toMessagePageByChatId(receiveId: string) {
        RouterUtil.navigateTo(UserPagePath.message + '?chatId=' + receiveId)
    }

    static toMessagePageByGroupId(groupId: string) {
        RouterUtil.navigateTo(UserPagePath.message + '?groupId=' + groupId)
    }

    static toIMGroupMember(groupId: string) {
        RouterUtil.navigateTo("/pages/chat/groupMember?id=" + groupId);
    }

    static toFaceValuePage() {
        SocialuniAppUtil.AlertUtil.confirm('是否查看颜值分介绍').then(() => {
            RouterUtil.navigateTo(UserPagePath.faceValueInfo)
        })
    }

    static toUserContactInfoPage() {
        RouterUtil.navigateTo(UserPagePath.userContactInfo)
    }

    static async toSocialUniAuth(authVO: SocialuniAuthQO) {
        let appId = 'wx0bf5fe9ceeab514c'
        if (socialuniSystemModule.isMpQQ) {
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
            return UserPagePath.webBrowser + '?title=' + pageTitle + '&url=' + webUrl
        }
        return null
    }

    /!**
     * 保留当前页面，跳转到应用内的某个页面，使用uni.navigateBack可以返回到原页面。
     * @param pagePath
     * @param params
     *!/

    /!*static navigateTo(pagePath: string, params?: object): void {
        console.log(pagePath)
        if (params) {
            const paramObj = new URLSearchParams()
            for (const key in params) {
                paramObj.append(key, params[key])
            }
            pagePath = pagePath + '?' + paramObj.toString()
        }
        uni.navigateTo({url: pagePath})
    }*!/

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

    /!**
     * 关闭当前页面，跳转到应用内的某个页面，需要跳转的应用内非 tabBar 的页面的路径。
     * @param pagePath
     *!/
    static redirectTo(pagePath: string): void {
        uni.redirectTo({url: pagePath})
    }

    /!**
     * 关闭所有页面，打开到应用内的某个页面。，如果跳转的页面路径是 tabBar 页面则不能带参数
     * @param pagePath
     *!/
    static reLaunch(pagePath: string): void {
        uni.reLaunch({url: pagePath})
    }

    /!**
     * 跳转到 tabBar 页面，并关闭其他所有非 tabBar 页面，跳转到 tabBar 页面只能使用 switchTab 跳转。
     * @param pagePath
     *!/
    static switchTab(pagePath: string): void {
        if (socialuniSystemModule.isApp) {
            uni.showTabBar()
        }
        uni.switchTab({url: pagePath})
    }

    /!**
     * 关闭当前页面，返回上一页面或多级页面。可通过 getCurrentPages() 获取当前的页面栈，决定需要返回几层。
     *!/


    static goBack(): void {
        uni.navigateBack({delta: 1})
    }

    static getCurrentPageURI(): string {
        return '/' + getCurrentPages()[getCurrentPages().length - 1].route
    }

    static getCurrentPage(): any {
        return getCurrentPages()[getCurrentPages().length - 1]
    }*/
}
