import LocationQueryQO from "socialuni-api-base/src/model/location/LocationQueryQO";
import {QQMapResult} from "socialuni-api-base/src/model/location/QQMapResult";
import ResultRO from "socialuni-api-base/src/model/social/ResultRO";
import socialuniAppRequest from "../socialuniAppRequest";

export default class SocialuniLocationAPI {
    static queryLocation(location: LocationQueryQO = new LocationQueryQO()): Promise<ResultRO<QQMapResult>> {
        //小程序，且注册了qq的地图key
        return socialuniAppRequest.post<QQMapResult>('socialuni/location/queryLocation', location) as any
    }
}
