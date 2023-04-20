import socialuniCommunityRequest from "../request/socialuniCommunityRequest";
import LocationQueryQO from "socialuni/src/model/location/LocationQueryQO";
import {QQMapResult} from "socialuni/src/model/location/QQMapResult";
import ResultRO from "socialuni/src/model/social/ResultRO";


export default class SocialuniLocationAPI {
    static queryLocation(location: LocationQueryQO = new LocationQueryQO()): Promise<ResultRO<QQMapResult>> {
        //小程序，且注册了qq的地图key
        return socialuniCommunityRequest.post<QQMapResult>('socialuni/location/queryLocation', location) as any
    }
}
