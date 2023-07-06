import SocialuniUserRO from "@socialuni/socialuni-api-base/src/model/user/SocialuniUserRO";
import ImgFileVO from "@socialuni/socialuni-api-base/src/model/ImgFileVO";
import CenterUserDetailRO from "@socialuni/socialuni-api-base/src/model/social/CenterUserDetailRO";
import socialuniUserRequest from "../request/socialuniUserRequest";

export default class SocialuniUserAPI {
    static queryUserDetailAPI(userId: string) {
        return socialuniUserRequest.get<CenterUserDetailRO>('socialuni/user/queryUserDetail/' + userId)
    }

    static getUserImgListAPI(userId: string) {
        return socialuniUserRequest.get<ImgFileVO[]>('socialuni/user/getUserImgList/' + userId)
    }

    static queryRecentlyUsersAPI() {
        return socialuniUserRequest.get<CenterUserDetailRO[]>('socialuni/user/queryRecentlyUsers')
    }
}
