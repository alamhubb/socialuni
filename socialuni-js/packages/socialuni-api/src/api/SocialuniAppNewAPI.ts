import request from "socialuni-api/src/request/request";
import ObjectUtil from "socialuni-sdk/src/utils/ObjectUtil";
import AppInitDataRO from "../model/config/AppInitDataRO";
import FrontErrorLogVO from "../model/FrontErrorLogVO";
import AppUpdateResultVO from "../model/app/AppUpdateResultVO";
import AppUpdateVO from "../model/app/AppUpdateVO";

export default class SocialuniAppNewAPI {
    static getAppConfigAPI() {
        return request.post<AppInitDataRO>('socialuni/socialuniApp/getAppLaunchData')
    }

    static sendErrorLogAPI(uri: string, detail: string, params?: any, errorMsg?: any) {
        //如果已经是发送错误日志，不再重复发送
        if (uri && uri.indexOf('sendErrorLog') > -1) {
            return
        }
        return request.post('socialuni/socialuniApp/sendErrorLog', new FrontErrorLogVO(uri, detail, ObjectUtil.toJson(params), ObjectUtil.toJson(errorMsg)))
    }

    static checkUpdateAPI(versionCode: number) {
        const appUpdate = new AppUpdateVO(versionCode)
        return request.post<AppUpdateResultVO>('socialuni/app/checkUpdate', appUpdate)
    }
}
