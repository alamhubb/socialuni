import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import socialuniUserRequest from "socialuni-user-api/src/request/socialuniUserRequest";
import PeiwanRO from "socialuni-admin-api/src/model/peiwan/PeiwanRO";
import ResultRO from "socialuni-api-base/src/model/social/ResultRO";
import DomFile from "qingjs/src/model/DomFile";
import AdminPeiwanAppInitData from "socialuni-admin-api/src/model/peiwan/AdminPeiwanAppInitData";
import SocialuniUserRO from "socialuni-api-base/src/model/user/SocialuniUserRO";
import SocialuniUserImgComRO from "socialuni-user-api/src/model/SocialuniUserImgComRO";

export default class SocialuniOperateUserAPI {

    static addOperateUser(addQO: SocialuniUserRO) {
        return socialuniUserRequest.post<void>('socialuni/admin/operateUser/createOperateUser', addQO)
    }

    static queryOperateUserList() {
        return socialuniUserRequest.get<SocialuniUserImgComRO[]>('socialuni/admin/operateUser/queryOperateUserList')
    }

    static addUserImgList(userId: string, imgs: DomFile[]) {
        return socialuniUserRequest.post<SocialuniUserImgComRO[]>(`socialuni/admin/operateUser/addUserImgList/${userId}`, imgs)
    }
}
