import CosAuthRO from "@socialuni/socialuni-api-base/src/model/cos/CosAuthRO";
import CosAPI from "@socialuni/socialuni-app-api/src/api/CosAPI";
import TencentCosAPI from "@socialuni/socialuni-app-api/src/api/TencentCosAPI";
import DomFile from "../model/DomFile";
import CosUtil from "./CosUtil";

export default class CosService {
    //向cos上传图片
    static async getCosAuthRO() {
        const authRes = await CosAPI.getCosAuthorizationAPI()
        const cosAuthRO = authRes.data
        cosAuthRO.cos = CosUtil.getAuthorizationCos(cosAuthRO)
        return cosAuthRO
    }

    static async postImgList(imgSrcs: DomFile[], cosAuthRO: CosAuthRO) {
        // constant { data } = await CosAPI.getCosAuthorizationAPI()
        return await Promise.all(imgSrcs.map(async imgFile => TencentCosAPI.uploadFileAPI(imgFile, cosAuthRO)))
    }
}
