/* eslint-disable */
// #ifdef MP
//@ts-ignore
import COS from 'cos-wx-sdk-v5'
// #endif
// #ifdef H5
//@ts-ignore
import COS from 'cos-js-sdk-v5'
// #endif
import AppMsg from '../constant/AppMsg'
import AlertUtil from './AlertUtil'
import CosAuthRO from '../model/cos/CosAuthRO'
import DomFile from '../model/DomFile'
import UniUtil from './UniUtil'

export default class CosUtil {
  //向cos上传图片
  static async postImg(imgFile: DomFile, cosAuthRO: CosAuthRO) {
    const cos = CosUtil.getAuthorizationCos(cosAuthRO)
    await CosUtil.postObjectBase(imgFile, cosAuthRO, cos)
  }

  private static async postObjectBase(imgFile: DomFile, data: CosAuthRO, cos) {
    return new Promise<any>(async (resolve, reject) => {
      const headers = {
        // "fileid": "bba022e9313849acafeb34fd5d5a65f5avatar.jpg"
        // 通过 imageMogr2 接口使用图片缩放功能：指定图片宽度为 200，宽度等比压缩
        'Pic-Operations':
          `{"is_pic_info": 1, "rules":[{"fileid": "${imgFile.fileName}!avatar", "rule": "imageMogr2/thumbnail/100x/interlace/0"},{"fileid": "${imgFile.fileName}!normal", "rule": "imageMogr2/thumbnail/800x/interlace/1"},{"fileid": "${imgFile.fileName}!thumbnail", "rule": "imageMogr2/thumbnail/300x/interlace/0"}]}`,
      }
      const uploadImgFile = await UniUtil.getFile(imgFile)
      console.log(uploadImgFile)
      cos.putObject({
        Bucket: data.bucket,
        Region: data.region,
        Key: imgFile.src,
        Body: uploadImgFile,
        Headers: headers,
        // {"fileid": "${imgFile.fileName}!avatar", "rule": "imageMogr2/thumbnail/100x/interlace/0|imageMogr2/gravity/center/crop/100x100"},
      }, (err, data) => {
        if (!err) {
          resolve(data)
        } else {
          UniUtil.hideLoading()
          AlertUtil.error(AppMsg.uploadFailMsg)
          reject(err)
        }
      })
    })
  }

  static postImgList(imgSrcs: DomFile[], cosAuthRO: CosAuthRO) {
    // constant { data } = await CosAPI.getCosAuthorizationAPI()
    const cos = CosUtil.getAuthorizationCos(cosAuthRO)
    // await Promise.all(imgSrcs.map(imgFile => CosUtil.postObjectBase(imgFile, data, cos)))
    imgSrcs.map(imgFile => CosUtil.postObjectBase(imgFile, cosAuthRO, cos))
  }

  static getAuthorizationCos(cosAuthRO: CosAuthRO): COS {
    const credentials = cosAuthRO && cosAuthRO.credentials
    if (!cosAuthRO || !credentials) return console.error('credentials invalid')
    const cosObj = new COS({
      // ForcePathStyle: true, // 如果使用了很多存储桶，可以通过打开后缀式，减少配置白名单域名数量，请求时会用地域域名
      getAuthorization(options, callback) {
        // 异步获取临时密钥
        callback({
          TmpSecretId: cosAuthRO.credentials.tmpSecretId,
          TmpSecretKey: cosAuthRO.credentials.tmpSecretKey,
          XCosSecurityToken: cosAuthRO.credentials.sessionToken,
          // 建议返回服务器时间作为签名的开始时间，避免用户浏览器本地时间偏差过大导致签名错误
          StartTime: cosAuthRO.startTime, // 时间戳，单位秒，如：1580000000
          ExpiredTime: cosAuthRO.expiredTime // 时间戳，单位秒，如：1580000900
        })
      }
    })
    return cosObj
  }
}
