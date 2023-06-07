import CosAuthRO from "socialuni-api-base/src/model/cos/CosAuthRO";
import CosAPI from "socialuni-app-api/src/api/CosAPI";
import TencentCosAPI from "socialuni-app-api/src/api/TencentCosAPI";
import CosUtil from "./CosUtil";
import DomFile from "../model/DomFile";

export default class CosService {
    //向cos上传图片
    static async getCosAuthRO() {
        const authRes = await CosAPI.getCosAuthorizationAPI()
        const cosAuthRO = authRes.data
        cosAuthRO.cos = CosUtil.getAuthorizationCos(cosAuthRO)
        console.log(45646545)
        console.log(cosAuthRO)
        return cosAuthRO
    }

    static async postImgList(imgSrcs: DomFile[], cosAuthRO: CosAuthRO) {
        // constant { data } = await CosAPI.getCosAuthorizationAPI()
        return await Promise.all(imgSrcs.map(async imgFile => TencentCosAPI.uploadFileAPI(imgFile, cosAuthRO)))
    }
}
