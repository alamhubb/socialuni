/* eslint-disable */
import JsonUtils from '@/utils/JsonUtil'
import CosAPI from '@/api/CosAPI'
import COS from 'cos-wx-sdk-v5'
import CosConst from '@/const/CosConst'
import ImgFileVO from '@/model/ImgFileVO'
import UniUtil from './UniUtil'
import AppMsg from '@/const/AppMsg'
import Alert from './Alert'

export default class CosUtil {
  static postObject (imgFile: ImgFileVO, userId: string, cos = CosUtil.getAuthorizationCos(userId)) {
    return new Promise<any>((resolve, reject) => {
      cos.postObject({
        Bucket: CosConst.bucketName,
        Region: CosConst.region,
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

  static postObjectList (imgSrcs: ImgFileVO[], userId: string) {
    const cos = CosUtil.getAuthorizationCos(userId)
    return Promise.all(imgSrcs.map(imgFile => CosUtil.postObject(imgFile, userId, cos)))
  }

  static getAuthorizationCos (userId) {
    return new COS({
      // ForcePathStyle: true, // 如果使用了很多存储桶，可以通过打开后缀式，减少配置白名单域名数量，请求时会用地域域名
      getAuthorization (options, callback) {
        // 异步获取临时密钥
        CosAPI.getCosAuthorizationAPI(userId).then((res: any) => {
          const data = JsonUtils.jsonParse(res.data)
          const credentials = data && data.credentials
          if (!data || !credentials) return console.error('credentials invalid')
          callback({
            TmpSecretId: credentials.tmpSecretId,
            TmpSecretKey: credentials.tmpSecretKey,
            XCosSecurityToken: credentials.sessionToken,
            // 建议返回服务器时间作为签名的开始时间，避免用户浏览器本地时间偏差过大导致签名错误
            StartTime: data.startTime, // 时间戳，单位秒，如：1580000000
            ExpiredTime: data.expiredTime // 时间戳，单位秒，如：1580000900
          })
        })
      }
    })
  }
}
