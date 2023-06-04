import PagePath from '../constant/PagePath'

export default class SkipUtil {
  static getWebUrl (webUrl: string, pageTitle = ''): string {
    return UserPagePath.webBrowser + '?title=' + pageTitle + '&url=' + webUrl
  }
}
