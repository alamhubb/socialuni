/* eslint-disable */
import COS from 'cos-wx-sdk-v5'
import AppMsg from '@/const/AppMsg'
import Alert from './Alert'
import CosAuthRO from '@/model/cos/CosAuthRO'
import DomFile from '@/model/DomFile'
import UniUtil from '@/utils/UniUtil'

export default class CosUtil {
  //向cos上传图片
  static async postImg (imgFile: DomFile, cosAuthRO: CosAuthRO) {
    const cos = CosUtil.getAuthorizationCos(cosAuthRO)
    await CosUtil.postObjectBase(imgFile, cosAuthRO, cos)
  }

  private static postObjectBase (imgFile: DomFile, data: CosAuthRO, cos) {
    return new Promise<any>((resolve, reject) => {
      cos.postObject({
        Bucket: data.bucket,
        Region: data.region,
        Key: imgFile.cosSrc,
        FilePath: imgFile.path
      }, (err, data) => {
        if (!err) {
          resolve(data)
        } else {
          UniUtil.hideLoading()
          Alert.error(AppMsg.uploadFailMsg)
          reject(err)
        }
      })
    })
  }

  static postImgList (imgSrcs: DomFile[], cosAuthRO: CosAuthRO) {
    // const { data } = await CosAPI.getCosAuthorizationAPI()
    const cos = CosUtil.getAuthorizationCos(cosAuthRO)
    // await Promise.all(imgSrcs.map(imgFile => CosUtil.postObjectBase(imgFile, data, cos)))
    imgSrcs.map(imgFile => CosUtil.postObjectBase(imgFile, cosAuthRO, cos))
  }

  static getAuthorizationCos (cosAuthRO: CosAuthRO): COS {
    const credentials = cosAuthRO && cosAuthRO.credentials
    if (!cosAuthRO || !credentials) return console.error('credentials invalid')
    const cosObj = new COS({
      // ForcePathStyle: true, // 如果使用了很多存储桶，可以通过打开后缀式，减少配置白名单域名数量，请求时会用地域域名
      getAuthorization (options, callback) {
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
