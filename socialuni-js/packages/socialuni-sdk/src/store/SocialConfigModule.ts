import {Pinia, Store} from "pinia-class-component"
import {socialConfigModule} from "./store";
import SocialuniAppNewAPI from "socialuni-api/src/api/SocialuniAppNewAPI";
import ReportAPI from "socialuni-api/src/api/ReportAPI";
import SocialuniAppConfigRO from "socialuni-api/src/model/config/SocialuniAppConfigRO";
import AppInitDataRO from "socialuni-api/src/model/config/AppInitDataRO";
import SocialuniAppMoreConfigRO from "socialuni-api/src/model/config/SocialuniAppMoreConfigRO";

@Store
export default class SocialConfigModule extends Pinia {
    // 动态页展示广告,设置一些默认值，在这里设置还是去使用的地方设置
    appConfig: SocialuniAppConfigRO = new SocialuniAppConfigRO()
    appMoreConfig: SocialuniAppMoreConfigRO = new SocialuniAppMoreConfigRO()
    reportTypes: string[] = []

    getAppConfigAction() {
        SocialuniAppNewAPI.getAppConfigAPI().then(res => {
            const appInitData: AppInitDataRO = res.data
            socialConfigModule.appConfig = appInitData.appConfig
            socialConfigModule.appMoreConfig = appInitData.appMoreConfig
        })
    }

    // actions
    getReportTypesAction() {
        ReportAPI.queryReportTypesAPI().then(res => {
            this.reportTypes = res.data
        })
    }
}
