import socialuniUserRequest from "socialuni-user-api/src/request/socialuniUserRequest";
import PeiwanRO from "socialuni-admin-api/src/model/peiwan/PeiwanRO";
export default class SocialuniPeiwanAPI {

    static queryPeiwanInfoListAPI() {
        return socialuniUserRequest.get<PeiwanRO[]>('socialuni/peiwan/queryPeiwanInfoList')
    }
}
