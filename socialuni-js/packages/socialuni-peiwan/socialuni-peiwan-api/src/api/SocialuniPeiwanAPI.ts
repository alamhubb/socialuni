import CenterUserDetailRO from "@socialuni/socialuni-api-base/src/model/social/CenterUserDetailRO";
import socialuniUserRequest from "@socialuni/socialuni-user-api/src/request/socialuniUserRequest";
import PeiwanRO from "@socialuni/socialuni-admin-api/src/model/peiwan/PeiwanRO";
import ResultRO from "@socialuni/socialuni-api-base/src/model/social/ResultRO";

export default class SocialuniPeiwanAPI {

    static addPeiwanInfoAPI(addQO: PeiwanRO) {
        return socialuniUserRequest.post<CenterUserDetailRO[]>('admin/peiwan/addPeiwanInfo', addQO)
    }

    static queryPeiwanInfoListAPI() {
        return socialuniUserRequest.get<PeiwanRO[]>('admin/peiwan/getPeiwanInfoList')
    }
}
