import {QQMapResult} from "../../model/location/QQMapResult";
import LocationQueryQO from "../../model/location/LocationQueryQO";
import ResultRO from "../../model/social/ResultRO";
import request from '../../request/request';

export default class SocialuniLocationAPI {
    static queryLocation(location: LocationQueryQO = new LocationQueryQO()): Promise<ResultRO<QQMapResult>> {
        //小程序，且注册了qq的地图key
        return request.post<QQMapResult>('socialuni/location/queryLocation', location) as any
    }
}
