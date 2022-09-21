import SkipType from '../constant/SkipType'
import SkipUrlConst from '../constant/SkipUrlConst'
import PagePath from '../constant/PagePath'
import { socialSystemModule } from '../store'
import PageUtil from '@/socialuni/utils/PageUtil'

export default class RouterUtil {
  static getWebUrl (webUrl: string, pageTitle = ''): string {
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
  static navigateTo (pagePath: string, params?: object): void {
    console.log(pagePath)
    if (params) {
      const paramObj = new URLSearchParams()
      for (const key in params) {
        paramObj.append(key, params[key])
      }
      pagePath = pagePath + '?' + paramObj.toString()
    }
    uni.navigateTo({ url: pagePath })
  }

  static navigateToAll (type: string, skipUrl: string, pageTitle: string) {
    if (type === SkipType.mp) {
      RouterUtil.navigateToMp(skipUrl, pageTitle)
    } else if (type === SkipType.web) {
      RouterUtil.navigateToWeb(skipUrl, pageTitle)
    } else if (type === SkipType.local) {
      RouterUtil.navigateTo(skipUrl)
    } else {
      RouterUtil.toWebHome()
    }
  }

  static toWebHome (): void {
    RouterUtil.navigateTo(SkipUrlConst.homeUrl())
  }


  static navigateToWeb (webUrl: string, pageTitle?: string): void {
    RouterUtil.navigateTo(RouterUtil.getWebUrl(webUrl, pageTitle))
  }

  static async navigateToMp (appId: string, path: string = null, extraData: any = null) {
    return new Promise((resolve, reject) => {
      uni.navigateToMiniProgram({
        appId: appId,
        path: path,
        extraData: extraData,
        success (res) {
          resolve(res)
        },
        fail (err) {
          reject(err)
        }
      })
    })
  }

  /**
   * 关闭当前页面，跳转到应用内的某个页面，需要跳转的应用内非 tabBar 的页面的路径。
   * @param pagePath
   */
  static redirectTo (pagePath: string): void {
    uni.redirectTo({ url: pagePath })
  }

  /**
   * 关闭所有页面，打开到应用内的某个页面。，如果跳转的页面路径是 tabBar 页面则不能带参数
   * @param pagePath
   */
  static reLaunch (pagePath: string): void {
    uni.reLaunch({ url: pagePath })
  }

  /**
   * 跳转到 tabBar 页面，并关闭其他所有非 tabBar 页面，跳转到 tabBar 页面只能使用 switchTab 跳转。
   * @param pagePath
   */
  static switchTab (pagePath: string): void {
    if (socialSystemModule.isApp) {
      uni.showTabBar()
    }
    uni.switchTab({ url: pagePath })
  }

  /**
   * 关闭当前页面，返回上一页面或多级页面。可通过 getCurrentPages() 获取当前的页面栈，决定需要返回几层。
   */
  static goBackOrHome (): void {
    const pages = getCurrentPages()
    if (pages.length === 1) {
      PageUtil.goHome()
    } else {
      RouterUtil.goBack()
    }
    // uni.navigateBack({ delta: 1 })
  }

  static goBackOrMine (): void {
    const pages = getCurrentPages()
    if (pages.length === 1) {
      PageUtil.toMinePage()
    } else {
      RouterUtil.goBack()
    }
    // uni.navigateBack({ delta: 1 })
  }

  static goBack (): void {
    uni.navigateBack({ delta: 1 })
  }

  static getCurrentPageURI (): string {
    return '/' + getCurrentPages()[getCurrentPages().length - 1].route
  }

  static getCurrentPage (): any {
    return getCurrentPages()[getCurrentPages().length - 1]
  }
}
