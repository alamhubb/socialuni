import router from '@/router'
import ObjectUtil from '@/components/YComponent/utils/ObjectUtil'
import AppUtil from "@/components/YComponent/utils/AppUtil";

export default class WindowUtil {
  static open(url: string, target?: string) {
    const routeData = router.resolve(url)
    console.log(routeData.href)
    window.open(routeData.href, target)
  }

  static openDownload(url: string) {
    const downloadUrl = AppUtil.getSystemRootPath() + url + AppUtil.getTokenStr()
    console.log(downloadUrl)
    window.open(downloadUrl)
  }

  static openDownloadAppend(url: string) {
    const downloadUrl = AppUtil.getSystemRootPath() + url + AppUtil.getAppendTokenStr()
    console.log(downloadUrl)
    window.open(downloadUrl)
  }

  static getV2Url(url: string) {
    if (url.startsWith('/')) {
      url = url.substring(1, url.length)
    }
    const v2Url = AppUtil.getSystemRootPath() + url + AppUtil.getTokenStr()
    console.log(v2Url)
    const downloadUrl = '/iframe?' + ObjectUtil.toParamData({url: v2Url})
    console.log(downloadUrl)
    return downloadUrl
  }

  static getOutUrl(url: string) {
    if (url.startsWith('/')) {
      url = url.substring(1, url.length)
    }
    const v2Url = AppUtil.getSystemRootPath() + url + AppUtil.getTokenStr()
    console.log(v2Url)
    return v2Url
  }
}

