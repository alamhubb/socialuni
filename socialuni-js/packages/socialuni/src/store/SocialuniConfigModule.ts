import {reactive} from "vue";
import SocialuniAppConfigRO from "../model/config/SocialuniAppConfigRO";
import SocialuniAppMoreConfigRO from "../model/config/SocialuniAppMoreConfigRO";
import AppInitDataRO from "../model/config/AppInitDataRO";
import SocialuniAppAPI from "../api/SocialuniAppAPI";
import ReportAPI from "../api/ReportAPI";

class SocialuniConfigModule {

    // 动态页展示广告,设置一些默认值，在这里设置还是去使用的地方设置
    appConfig: SocialuniAppConfigRO = new SocialuniAppConfigRO()
    appMoreConfig: SocialuniAppMoreConfigRO = new SocialuniAppMoreConfigRO()
    reportTypes: string[] = []

    getAppConfigAction() {
        SocialuniAppAPI.getAppConfigAPI().then(res => {
            const appInitData: AppInitDataRO = res.data
            this.appConfig = appInitData.appConfig
            this.appMoreConfig = appInitData.appMoreConfig
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
