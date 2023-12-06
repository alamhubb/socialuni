import {reactive} from "vue";
import SocialuniAppConfigRO from "socialuni-api-base/src/model/config/SocialuniAppConfigRO";
import SocialuniAppMoreConfigRO from "socialuni-api-base/src/model/config/SocialuniAppMoreConfigRO";
import SocialuniAppAPI from "socialuni-app-api/src/api/SocialuniAppAPI";
import AppInitDataRO from "socialuni-api-base/src/model/config/AppInitDataRO";
import ReportAPI from "socialuni-app-api/src/api/ReportAPI";

class SocialuniConfigModule {

    appName = '测试系统'
    // 动态页展示广告,设置一些默认值，在这里设置还是去使用的地方设置
    allConfig: AppInitDataRO = new AppInitDataRO()
    appConfig: SocialuniAppConfigRO = new SocialuniAppConfigRO()
    appMoreConfig: SocialuniAppMoreConfigRO = new SocialuniAppMoreConfigRO()
    reportTypes: string[] = []

    async getAppConfigAction() {
        await SocialuniAppAPI.getAppConfigAPI().then(res => {
            const appInitData: AppInitDataRO = res.data
            this.allConfig = res.data
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
