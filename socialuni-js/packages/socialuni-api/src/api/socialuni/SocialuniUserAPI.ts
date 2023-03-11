import request from '../../request/request';
import SocialuniUserRO from "../../model/user/SocialuniUserRO";
import ImgFileVO from "../../model/ImgFileVO";
import SocialUserContentRO from "../../model/social/SocialUserContentRO";
import UserQueryVO from "../../model/user/UserQueryVO";
import CenterUserDetailRO from "../../model/social/CenterUserDetailRO";
import SocialuniUserExtendFriendQueryQO from "../../model/user/SocialuniUserExtendFriendQueryQO";

export default class SocialuniUserAPI {
    static queryUserDetailAPI(userId: string) {
        return request.get<SocialuniUserRO>('socialuni/user/queryUserDetail/' + userId)
    }

    static getUserImgListAPI(userId: string) {
        return request.get<ImgFileVO[]>('socialuni/user/getUserImgList/' + userId)
    }

    static queryRecentlyUsersAPI() {
        return request.get<CenterUserDetailRO[]>('socialuni/user/queryRecentlyUsers')
    }
}
