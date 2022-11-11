/* eslint-disable */
// #ifdef MP
//@ts-ignore
import COS from 'cos-wx-sdk-v5'
// #endif
// #ifdef H5
//@ts-ignore
import COS from 'cos-js-sdk-v5'
// #endif
import AppMsg from "socialuni-constant/constant/AppMsg";
import TencentCosIdInfoRO from "socialuni-common/src/model/RO/tencent/cos/idImgInfo/TencentCosIdInfoRO";
import request from "../request/request";
import UniUtil from "../../../socialuni-user/src/utils/UniUtil";
import CosAuthRO from "../model/cos/CosAuthRO";
import CosUploadResult from "../model/cos/CosUploadResult";
import DomFile from "../model/DomFile";

export default class TencentCosAPI {
    static async getImgTagAPI(imgUrl, imgKey, cosAuthRO: CosAuthRO) {
        const authKey = COS.getAuthorization({
            SecretId: cosAuthRO.credentials.tmpSecretId,
            SecretKey: cosAuthRO.credentials.tmpSecretKey,
            Method: 'get',
            Key: imgKey
        })
        console.log(authKey)
        const res = await request.get('https://' + imgUrl + '?ci-process=detect-label', {
            headers: {
                Authorization: authKey,
                'x-cos-security-token': cosAuthRO.credentials.sessionToken
            }
        })
        return res
        /*
        const res = await cosAuthRO.cos.getObject({
          Bucket: cosAuthRO.bucket,
          Region: cosAuthRO.region,
          DataType: 'blob',
          QueryString: 'ci-process=detect-label',
          Key: imgUrl,
          onProgress: function (progressData) {
            console.log(JSON.stringify(progressData))
          }
        })*/
    }

    static async getImgTextContentAPI(imgUrl, imgKey, cosAuthRO: CosAuthRO) {
        const authKey = COS.getAuthorization({
            SecretId: cosAuthRO.credentials.tmpSecretId,
            SecretKey: cosAuthRO.credentials.tmpSecretKey,
            Method: 'get',
            Key: imgKey
        })
        console.log(authKey)
        const res = await request.get('https://' + imgUrl + '?ci-process=OCR', {
            headers: {
                Authorization: authKey,
                'x-cos-security-token': cosAuthRO.credentials.sessionToken
            }
        })
        console.log(res)
        return res
        /*
        const res = await cosAuthRO.cos.getObject({
          Bucket: cosAuthRO.bucket,
          Region: cosAuthRO.region,
          DataType: 'blob',
          QueryString: 'ci-process=detect-label',
          Key: imgUrl,
          onProgress: function (progressData) {
            console.log(JSON.stringify(progressData))
          }
        })*/
    }

    static async getIdCardInfoAPI(imgUrl, imgKey, cosAuthRO: CosAuthRO) {
        const authKey = COS.getAuthorization({
            SecretId: cosAuthRO.credentials.tmpSecretId,
            SecretKey: cosAuthRO.credentials.tmpSecretKey,
            Method: 'get',
            Key: imgKey
        })
        const res: string = await request.get('https://' + imgUrl + '?ci-process=IDCardOCR', {
            headers: {
                Authorization: authKey,
                'x-cos-security-token': cosAuthRO.credentials.sessionToken
            }
        }) as any
        return new TencentCosIdInfoRO(res)
    }

    static async uploadFileAPI(imgFile: DomFile, cosAuthRO: CosAuthRO) {
        return new Promise<CosUploadResult>(async (resolve, reject) => {
            const headers = {
                // "fileid": "bba022e9313849acafeb34fd5d5a65f5avatar.jpg"
                // 通过 imageMogr2 接口使用图片缩放功能：指定图片宽度为 200，宽度等比压缩
                'Pic-Operations':
                    `{"is_pic_info": 1, "rules":[{"fileid": "${imgFile.fileName}!avatar", "rule": "imageMogr2/thumbnail/100x/interlace/0"},{"fileid": "${imgFile.fileName}!normal", "rule": "imageMogr2/thumbnail/800x/interlace/1"},{"fileid": "${imgFile.fileName}!thumbnail", "rule": "imageMogr2/thumbnail/300x/interlace/0"}]}`,
            }
            const uploadImgFile = await UniUtil.getFile(imgFile) as any
            cosAuthRO.cos.putObject({
                Bucket: cosAuthRO.bucket,
                Region: cosAuthRO.region,
                Key: imgFile.src,
                Body: uploadImgFile,
                Headers: headers,
                // {"fileid": "${imgFile.fileName}!avatar", "rule": "imageMogr2/thumbnail/100x/interlace/0|imageMogr2/gravity/center/crop/100x100"},
            }, (err, data: CosUploadResult) => {
                if (!err) {
                    resolve(data)
                } else {
                    AlertUtil.error(AppMsg.uploadFailMsg)
                    reject(err)
                }
            })
        })
    }
}
