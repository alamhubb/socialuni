import {useRouter} from "vue-router";
import {getCurrentInstance} from "vue";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";

export default class RouterUtil {
    /**
     * 保留当前页面，跳转到应用内的某个页面，使用uni.navigateBack可以返回到原页面。
     * @param pagePath
     * @param params
     */
    static navigateTo(pagePath: string, params?: object): void {
        //微信小程序不支持URLSearchParams
        /*if (params) {
            const paramObj = new URLSearchParams()
            for (const key in params) {
                paramObj.append(key, params[key])
            }
            pagePath = pagePath + '?' + paramObj.toString()
        }*/
        uni.navigateTo({url: pagePath})
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
        // if (socialuniSystemModule.isApp) {
        uni.showTabBar()
        // }
        uni.switchTab({url: pagePath})
    }

    static goBack(): void {
        uni.navigateBack({delta: 1})
    }

    static getCurrentPageURI(): string {
        return socialuniPluginsModule.router.currentRoute.path
    }

    static getCurrentPage(): any {
        return getCurrentPages()[getCurrentPages().length - 1]
    }
}
