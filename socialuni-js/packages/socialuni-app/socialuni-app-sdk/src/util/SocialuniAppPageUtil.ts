import SkipType from "@socialuni/socialuni-constant/constant/SkipType";
import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";
import SocialuniAppPagePath from "../constant/SocialuniAppPagePath";
import SkipUrlConst from "../constant/SkipUrlConst";


export default class SocialuniAppPageUtil {
    static navigateToAll(type: string, skipUrl: string, pageTitle: string) {
        if (type === SkipType.mp) {
            SocialuniAppUtil.RouterUtil.navigateToMp(skipUrl, pageTitle)
        } else if (type === SkipType.web) {
            SocialuniAppPageUtil.navigateToWeb(skipUrl, pageTitle)
        } else if (type === SkipType.local) {
            SocialuniAppUtil.RouterUtil.navigateTo(skipUrl)
        } else {
            SocialuniAppPageUtil.toWebHome()
        }
    }


    static navigateToWeb(webUrl: string, pageTitle?: string): void {
        SocialuniAppUtil.RouterUtil.navigateTo(SocialuniAppPageUtil.getWebUrl(webUrl, pageTitle))
    }

    static toWebHome(): void {
        SocialuniAppUtil.RouterUtil.navigateTo(SkipUrlConst.homeUrl())
    }

    static getWebUrl(webUrl: string, pageTitle = ''): string {
        if (webUrl) {
            return SocialuniAppPagePath.webBrowser + '?title=' + pageTitle + '&url=' + webUrl
        }
        return null
    }
}
