/* eslint-disable */
import COS from 'cos-js-sdk-v5'
import CosAuthRO from '@/model/cos/CosAuthRO'

export default class H5CosUtil {
  static getAuthorizationH5Cos(cosAuthRO: CosAuthRO): COS {
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
