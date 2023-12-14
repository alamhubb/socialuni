import SkipType from "socialuni-constant/constant/SkipType";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import SocialuniAppPagePath from "../constant/SocialuniAppPagePath";
import SkipUrlConst from "../constant/SkipUrlConst";


export default class SocialuniAppPageUtil {
    static navigateToAll(type: string, skipUrl: string, pageTitle: string) {
        if (type === SkipType.mp) {
            QingAppUtil.RouterUtil.navigateToMp(skipUrl, pageTitle)
        } else if (type === SkipType.web) {
            SocialuniAppPageUtil.navigateToWeb(skipUrl, pageTitle)
        } else if (type === SkipType.local) {
            QingAppUtil.RouterUtil.navigateTo(skipUrl)
        } else {
            SocialuniAppPageUtil.toWebHome()
        }
    }


    static navigateToWeb(webUrl: string, pageTitle?: string): void {
        QingAppUtil.RouterUtil.navigateTo(SocialuniAppPageUtil.getWebUrl(webUrl, pageTitle))
    }

    static toWebHome(): void {
        QingAppUtil.RouterUtil.navigateTo(SkipUrlConst.homeUrl())
    }

    static getWebUrl(webUrl: string, pageTitle = ''): string {
        if (webUrl) {
            return SocialuniAppPagePath.webBrowser + '?title=' + pageTitle + '&url=' + webUrl
        }
        return null
    }
}
