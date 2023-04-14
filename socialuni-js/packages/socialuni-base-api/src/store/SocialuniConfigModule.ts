import {reactive} from "vue";
import SocialuniAppConfigRO from "../model/config/SocialuniAppConfigRO";
import SocialuniAppMoreConfigRO from "../model/config/SocialuniAppMoreConfigRO";
import AppInitDataRO from "../model/config/AppInitDataRO";
import SocialuniAppAPI from "../api/SocialuniAppAPI";
import ReportAPI from "socialuni-api/src/api/ReportAPI";

class SocialuniConfigModule {
    readonly socialuniUrl = (import.meta.env.VITE_APP_SOCIALUNI_URL || 'https://api.socialuni.cn') + '/'
    readonly socialuniUserUrl = (import.meta.env.VITE_APP_SOCIALUNI_USER_URL || this.socialuniUrl) + '/'
    readonly socialuniImUrl = (import.meta.env.VITE_APP_SOCIALUNI_IM_URL || this.socialuniUrl) + '/'
    readonly socialuniImWebsocketUrl = (import.meta.env.VITE_APP_SOCIALUNI_IM_WEBSOCKET_URL) + '/'
    readonly socialuniSecretKey = import.meta.env.VITE_APP_SOCIALUNI_SECRET_KEY || null

    // 动态页展示广告,设置一些默认值，在这里设置还是去使用的地方设置
    appConfig: SocialuniAppConfigRO = new SocialuniAppConfigRO()
    appMoreConfig: SocialuniAppMoreConfigRO = new SocialuniAppMoreConfigRO()
    reportTypes: string[] = []

    getAppConfigAction() {
        SocialuniAppAPI.getAppConfigAPI().then(res => {
            const appInitData: AppInitDataRO = res.data
            socialuniConfigModule.appConfig = appInitData.appConfig
            socialuniConfigModule.appMoreConfig = appInitData.appMoreConfig
        })
    }

    // actions
    getReportTypesAction() {
        ReportAPI.queryReportTypesAPI().then(res => {
            this.reportTypes = res.data
        })
    }
}

export const socialuniConfigModule: SocialuniConfigModule = reactive(new SocialuniConfigModule())
