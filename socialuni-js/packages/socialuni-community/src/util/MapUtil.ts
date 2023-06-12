import {QQMapResult} from "socialuni-api-base/src/model/location/QQMapResult";
import LocationQueryQO from "socialuni-api-base/src/model/location/LocationQueryQO";
import ResultRO from "socialuni-api-base/src/model/social/ResultRO";
import SocialuniLocationAPI from "socialuni-community-api/src/api/SocialuniLocationAPI";

export default class MapUtil {
    static async getLocationBySdk(): Promise<unknown> {
        return new Promise((resolve, reject) => {
            uni.getLocation({
                type: 'gcj02 ',
                success(res) {
                    //小程序，且注册了qq的地图key
                    SocialuniLocationAPI.queryLocation(new LocationQueryQO(res.latitude, res.longitude)).then((res: ResultRO<QQMapResult>) => {
                        resolve(res.data)
                    }).catch(() => {
                        reject()
                    })
                },
                fail() {
                    reject()
                }
            })
        })
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
