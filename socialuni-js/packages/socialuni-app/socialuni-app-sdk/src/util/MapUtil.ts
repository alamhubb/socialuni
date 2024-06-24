import {QQMapResult} from "socialuni-api-base/src/model/location/QQMapResult";
import LocationQueryQO from "socialuni-api-base/src/model/location/LocationQueryQO";
import ResultRO from "socialuni-api-base/src/model/social/ResultRO";
import SocialuniLocationAPI from "socialuni-community-api/src/api/SocialuniLocationAPI";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";

export default class MapUtil {
    static async getLocationBySdk(): Promise<unknown> {
        return QingAppUtil.NativeUtil.getLocation()
    }

    static getLocationByWeb(): Promise<QQMapResult> {
        return new Promise((resolve, reject) => {
            //其他平台需借用后台
            SocialuniLocationAPI.queryLocation().then((res: ResultRO<QQMapResult>) => {
                resolve(res.data)
            }).catch(() => {
                reject()
            })
        })
    }
}
