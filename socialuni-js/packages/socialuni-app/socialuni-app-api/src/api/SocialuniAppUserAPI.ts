import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import socialuniAppRequest from "../socialuniAppRequest";

export default class SocialuniAppUserAPI {
    static queryUserDetailAPI(userId: string) {
        return socialuniAppRequest.get<CenterUserDetailRO>('socialuni/appUser/queryUserDetail/' + userId)
    }
}
