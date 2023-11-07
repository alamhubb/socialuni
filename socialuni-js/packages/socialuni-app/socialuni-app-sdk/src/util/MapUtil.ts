import {QQMapResult} from "@socialuni/socialuni-api-base/src/model/location/QQMapResult";
import LocationQueryQO from "@socialuni/socialuni-api-base/src/model/location/LocationQueryQO";
import ResultRO from "@socialuni/socialuni-api-base/src/model/social/ResultRO";
import SocialuniLocationAPI from "@socialuni/socialuni-community-api/src/api/SocialuniLocationAPI";
import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";

export default class MapUtil {
    static async getLocationBySdk(): Promise<unknown> {
        return SocialuniAppUtil.NativeUtil.getLocation()
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
