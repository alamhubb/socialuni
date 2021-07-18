/* eslint-disable */
import COS from 'cos-wx-sdk-v5'
import ImgFileVO from '@/model/ImgFileVO'
import AppMsg from '@/const/AppMsg'
import Alert from './Alert'
import CosAuthRO from '@/model/cos/CosAuthRO'
import DomFile from '@/model/DomFile'
import CosAPI from '@/api/CosAPI'
import UniUtil from '@/utils/UniUtil'

export default class CosUtil {
  //向cos上传图片
  static async postObject (imgFile: ImgFileVO) {
    const { data } = await CosAPI.getCosAuthorizationAPI()
    console.log(data)
    const cos = CosUtil.getAuthorizationCos(data)
    return CosUtil.postObjectBase(imgFile, data, cos)
  }

  static postObjectBase (imgFile: DomFile, data: CosAuthRO, cos) {
    return new Promise<any>((resolve, reject) => {
      imgFile.src =  data.path + imgFile.src
      console.log(imgFile.src)
      cos.postObject({
        Bucket: data.bucket,
        Region: data.region,
        Key: imgFile.src,
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

  static async postObjectList (imgSrcs: ImgFileVO[]) {
    const { data } = await CosAPI.getCosAuthorizationAPI()
    console.log(data)
    const cos = CosUtil.getAuthorizationCos(data)
    return Promise.all(imgSrcs.map(imgFile => CosUtil.postObjectBase(imgFile, data, cos)))
  }

  static getAuthorizationCos (cosAuthRO: CosAuthRO): COS {
    const credentials = cosAuthRO && cosAuthRO.credentials
    if (!cosAuthRO || !credentials) return console.error('credentials invalid')
    const cosObj = new COS({
      // ForcePathStyle: true, // 如果使用了很多存储桶，可以通过打开后缀式，减少配置白名单域名数量，请求时会用地域域名
      getAuthorization (options, callback) {
        console.log(options)
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
