import AlertUtil from 'src/util/AlertUtil'
import AppConfig from "socialuni-constant/constant/AppConfig";
import AppUpdateType from "socialuni-constant/constant/AppUpdateType";
import SocialuniAppAPI from "socialuni/src/api/socialuni/SocialuniAppAPI";
import {socialuniConfigModule} from "socialuni/src/store/SocialuniConfigModule";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";

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
                    QingAppUtil.AlertUtil.confirm(hint, '安装').then(() => {
                        plus.runtime.openURL(updateUrl)
                    })
                } else if (AppUpdateType.hot === updateType) {
                    QingAppUtil.UniUtil.install(updateUrl).then(() => {
                        QingAppUtil.AlertUtil.confirm('新版本更新成功，是否现在重启清池app', '重启', '稍后').then(() => {
                            plus.runtime.restart()
                        })
                    }).catch(() => {
                        QingAppUtil.AlertUtil.hint('更新失败，' + socialuniConfigModule.appMoreConfig.errorMsgContactService)
                    })
                }
            })
        })
    }

   /* static createRewardedVideoAd() {
        return QingAppUtil.UniUtil.createRewardedVideoAd(AppConfig.app_award_ad_id)
    }*/
}
