import {reactive} from "vue";
import SocialuniAppConfigRO from "socialuni-api-base/src/model/config/SocialuniAppConfigRO";
import SocialuniAppMoreConfigRO from "socialuni-api-base/src/model/config/SocialuniAppMoreConfigRO";
import SocialuniAppAPI from "socialuni-app-api/src/api/SocialuniAppAPI";
import AppInitDataRO from "socialuni-api-base/src/model/config/AppInitDataRO";
import ReportAPI from "socialuni-app-api/src/api/ReportAPI";
import SocialuniDeviceUidUtil from "socialuni-user-sdk/src/util/SocialuniDeviceUidUtil";
import JsonUtil from "qing-util/src/util/JsonUtil";
import SocialuniLoginService from "socialuni-user-sdk/src/logic/SocialuniLoginService";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";

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


    async getDeviceUidAction() {
        const deviceUid = SocialuniDeviceUidUtil.get()
        if (!deviceUid) {
            const deviceObj = {
                appVersion: navigator.userAgent,
            };
            const device = JsonUtil.toJson(deviceObj)
            await SocialuniAppAPI.getDeviceUidAPI({device}).then(res => {
                SocialuniDeviceUidUtil.set(res.data)
                if (socialuniSystemModule.isH5) {
                    SocialuniLoginService.deviceUidLogin()
                }
            })
        }
    }

    // actions
    getReportTypesAction() {
        ReportAPI.queryReportTypesAPI().then(res => {
            this.reportTypes = res.data
        })
    }
}

export const socialuniConfigModule: SocialuniConfigModule = reactive(new SocialuniConfigModule())
