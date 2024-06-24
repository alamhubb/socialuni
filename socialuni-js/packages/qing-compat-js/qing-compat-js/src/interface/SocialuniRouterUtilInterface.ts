export interface SocialuniRouterUtilInterface {

    /**
     * 保留当前页面，跳转到应用内的某个页面，使用uni.navigateBack可以返回到原页面。
     * @param pagePath
     * @param params
     */
    push(pagePath: string, params?: object)

    /**
     * 保留当前页面，跳转到应用内的某个页面，使用uni.navigateBack可以返回到原页面。
     * @param pagePath
     * @param params
     */
    navigateTo(pagePath: string, params?: object): void

    /**
     * 关闭当前页面，跳转到应用内的某个页面，需要跳转的应用内非 tabBar 的页面的路径。
     * @param pagePath
     */
    redirectTo(pagePath: string): void

    /**
     * 关闭所有页面，打开到应用内的某个页面。，如果跳转的页面路径是 tabBar 页面则不能带参数
     * @param pagePath
     */
    reLaunch(pagePath: string): void

    /**
     * 跳转到 tabBar 页面，并关闭其他所有非 tabBar 页面，跳转到 tabBar 页面只能使用 switchTab 跳转。
     * @param pagePath
     */
    switchTab(pagePath: string): void

    goBack(): void

    getCurrentPageURI(): string

    getCurrentPage(): any

    goBackOrHome(): void

    goHome(): void

    getCurrentPageParams(): any

    navigateToMp(appId: string, path?: string, extraData?: any)
}
