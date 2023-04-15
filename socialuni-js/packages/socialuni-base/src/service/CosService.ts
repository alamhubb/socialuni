/* eslint-disable */
// #ifdef MP
//@ts-ignore
import COS from 'cos-wx-sdk-v5'
// #endif
// #ifdef H5
//@ts-ignore
import COS from 'cos-js-sdk-v5'
// #endif
import CosUtil from "socialuni-util/src/util/CosUtil";
import CosAuthRO from "socialuni-base-api/src/model/cos/CosAuthRO";
import CosAPI from "socialuni-base-api/src/api/CosAPI";
import DomFile from "socialuni-util/src/model/DomFile";
import TencentCosAPI from "socialuni-base-api/src/api/TencentCosAPI";

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
