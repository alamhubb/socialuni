import socialuniAppRequest from "../socialuniAppRequest";
import AppInitDataRO from "socialuni-api-base/src/model/config/AppInitDataRO";
import AppUpdateResultVO from "socialuni-api-base/src/model/app/AppUpdateResultVO";
import HomeSwiperVO from "socialuni-api-base/src/model/HomeSwiperVO";
import SocialuniTalkTabRO from "socialuni-api-base/src/model/talk/SocialuniTalkTabRO";
import FrontErrorLogVO from "socialuni-api-base/src/model/FrontErrorLogVO";
import JsonUtil from "qing-util/src/util/JsonUtil";
import AppUpdateVO from "socialuni-api-base/src/model/app/AppUpdateVO";


export default class SocialuniAppAPI {
    static getImgPathAPI() {
        return socialuniAppRequest.post<string>('socialuni/app/getImgPath')
    }

    static getAppConfigAPI() {
        return socialuniAppRequest.post<AppInitDataRO>('socialuni/app/getAppLaunchData')
    }

    static getDeviceUidAPI(deviceData: { device: string }) {
        return socialuniAppRequest.post<string>('socialuni/app/getDeviceUid', deviceData)
    }

    static checkUpdateAPI(versionCode: number) {
        const appUpdate = new AppUpdateVO(versionCode)
        return socialuniAppRequest.post<AppUpdateResultVO>('socialuni/app/checkUpdate', appUpdate)
    }

    static queryHomeSwipersAPI() {
        return socialuniAppRequest.post<HomeSwiperVO[]>('socialuni/app/queryHomeSwipers')
    }

    static queryHomeTabsAPI() {
        return socialuniAppRequest.post<SocialuniTalkTabRO[]>('socialuni/app/queryHomeTabs')
    }

    static sendErrorLogAPI(uri: string, frontPage: string, detail: string, params?: any, errorMsg?: any) {
        //如果已经是发送错误日志，不再重复发送
        if (uri && uri.indexOf('sendErrorLog') > -1) {
            return
        }
        return socialuniAppRequest.post('socialuni/app/sendErrorLog', new FrontErrorLogVO(uri, detail, JsonUtil.toJson(params), JsonUtil.toJson(errorMsg), frontPage))
    }
}
