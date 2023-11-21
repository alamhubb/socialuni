import AppUpdateType from "socialuni-constant/constant/AppUpdateType";
import SocialuniAppAPI from "socialuni-app-api/src/api/SocialuniAppAPI";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";

export default class APPUtil {
    static checkUpdate() {
        plus.runtime.getProperty(plus.runtime.appid, (widgetInfo) => {
            const version = widgetInfo.version.split('.').join('')
            SocialuniAppAPI.checkUpdateAPI(Number(version)).then((res) => {
                const updateType = res.data.updateType
                const updateUrl = res.data.updateUrl
                const updateHint = res.data.updateHint
                if (AppUpdateType.install === updateType) {
                    const hint = updateHint || '应用有新版本需要安装，点击安装即可更新'
                    SocialuniAppUtil.AlertUtil.confirm(hint, '安装').then(() => {
                        plus.runtime.openURL(updateUrl)
                    })
                } else if (AppUpdateType.hot === updateType) {
                    SocialuniAppUtil.NativeUtil.install(updateUrl).then(() => {
                        SocialuniAppUtil.AlertUtil.confirm('新版本更新成功，是否现在重启清池app', '重启', '稍后').then(() => {
                            plus.runtime.restart()
                        })
                    }).catch(() => {
                        SocialuniAppUtil.AlertUtil.hint('更新失败，' + socialuniConfigModule.appMoreConfig.errorMsgContactService)
                    })
                }
            })
        })
    }

   /* static createRewardedVideoAd() {
        return SocialuniAppUtil.UniUtil.createRewardedVideoAd(AppConfig.app_award_ad_id)
    }*/
}
