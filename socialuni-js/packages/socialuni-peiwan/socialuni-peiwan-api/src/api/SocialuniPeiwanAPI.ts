import CenterUserDetailRO from "@socialuni/socialuni-api-base/src/model/social/CenterUserDetailRO";
import socialuniUserRequest from "@socialuni/socialuni-user-api/src/request/socialuniUserRequest";
import PeiwanRO from "@socialuni/socialuni-admin-api/src/model/peiwan/PeiwanRO";
import ResultRO from "@socialuni/socialuni-api-base/src/model/social/ResultRO";
import DomFile from "@socialuni/socialuni-native-util/src/model/DomFile";

export default class SocialuniPeiwanAPI {

    static addPeiwanInfoAPI(addQO: PeiwanRO) {
        return socialuniUserRequest.post<CenterUserDetailRO[]>('admin/peiwan/addPeiwanInfo', addQO)
    }

    static queryPeiwanInfoListAPI() {
        return socialuniUserRequest.get<PeiwanRO[]>('admin/peiwan/queryPeiwanInfoList')
    }

    static updatePeiwanListAPI(peiwans: PeiwanRO[]) {
        return socialuniUserRequest.post<void>('admin/peiwan/updatePeiwanList', peiwans)
    }

    static updatePeiwanAvatarAPI(userId: string, src: string) {
        return socialuniUserRequest.get<string>(`admin/peiwan/updatePeiwanAvatar/${userId}?src=${src}`)
    }

    static addPeiwanImgListAPI(userId: string, imgs: DomFile[]) {
        return socialuniUserRequest.post<void>(`admin/peiwan/addPeiwanImgList/${userId}`, imgs)
    }
}
