import {Pinia, Store} from "pinia-class-component"
import SocialuniAppConfigRO from "../model/config/SocialuniAppConfigRO";
import SocialuniAppMoreConfigRO from "../model/config/SocialuniAppMoreConfigRO";
import SocialuniAppNewAPI from "../api/SocialuniAppNewAPI";
import {socialConfigModule} from "./store";
import AppInitDataRO from "../model/config/AppInitDataRO";
import ReportAPI from "../api/ReportAPI";

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
