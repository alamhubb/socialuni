
import {socialuniConfigModule} from "./store";
import ReportAPI from "socialuni-base-api/src/api/ReportAPI";
import SocialuniAppConfigRO from "socialuni-base-api/src/model/config/SocialuniAppConfigRO";
import AppInitDataRO from "socialuni-base-api/src/model/config/AppInitDataRO";
import SocialuniAppMoreConfigRO from "socialuni-base-api/src/model/config/SocialuniAppMoreConfigRO";
import SocialuniAppAPI from "socialuni-base/src/api/socialuni/SocialuniAppAPI";


class socialuniConfigModule {
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
