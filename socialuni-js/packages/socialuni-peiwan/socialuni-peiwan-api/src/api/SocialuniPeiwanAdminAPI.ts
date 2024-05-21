import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import socialuniUserRequest from "socialuni-user-api/src/request/socialuniUserRequest";
import PeiwanRO from "socialuni-admin-api/src/model/peiwan/PeiwanRO";
import ResultRO from "socialuni-api-base/src/model/social/ResultRO";
import DomFile from "qingjs/src/model/DomFile";
import AdminPeiwanAppInitData from "socialuni-admin-api/src/model/peiwan/AdminPeiwanAppInitData";

export default class SocialuniPeiwanAdminAPI {

    static getAppInitDataAPI() {
        return socialuniUserRequest.get<AdminPeiwanAppInitData>('socialuni/admin/peiwan/getAppInitData')
    }


    static addPeiwanInfoAPI(addQO: PeiwanRO) {
        return socialuniUserRequest.post<CenterUserDetailRO[]>('socialuni/admin/peiwan/addPeiwanInfo', addQO)
    }

    static queryPeiwanInfoListAPI() {
        return socialuniUserRequest.get<PeiwanRO[]>('socialuni/admin/peiwan/queryPeiwanInfoList')
    }

    static updatePeiwanListAPI(peiwans: PeiwanRO[]) {
        return socialuniUserRequest.post<void>('socialuni/admin/peiwan/updatePeiwanList', peiwans)
    }

    static addPeiwanInfoListAPI(peiwans: PeiwanRO[]) {
        return socialuniUserRequest.post<void>('socialuni/admin/peiwan/addPeiwanInfoList', peiwans)
    }

    static updatePeiwanAvatarAPI(userId: string, src: string) {
        return socialuniUserRequest.get<string>(`socialuni/admin/peiwan/updatePeiwanAvatar/${userId}?src=${src}`)
    }

    static deletePeiwanImgAPI(imgId: string) {
        return socialuniUserRequest.get<string>(`socialuni/admin/peiwan/deletePeiwanImg/${imgId}`)
    }

    static addPeiwanImgListAPI(userId: string, imgs: DomFile[]) {
        return socialuniUserRequest.post<void>(`socialuni/admin/peiwan/addPeiwanImgList/${userId}`, imgs)
    }
}
