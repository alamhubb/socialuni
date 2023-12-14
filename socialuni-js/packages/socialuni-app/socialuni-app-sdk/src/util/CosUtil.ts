import CosAuthRO from "socialuni-api-base/src/model/cos/CosAuthRO";
//@ts-ignore
import WxCOS from 'cos-wx-sdk-v5'
//@ts-ignore
import JsCOS from 'cos-js-sdk-v5'
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";

export default class CosUtil {
    static getAuthorizationCos(cosAuthRO: CosAuthRO): JsCOS {
        const credentials = cosAuthRO && cosAuthRO.credentials
        //@ts-ignore
        if (!cosAuthRO || !credentials) return console.error('credentials invalid')
        if (socialuniSystemModule.isUniApp && socialuniSystemModule.isMp) {
            const cosObj = new WxCOS({
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
        } else {
            const cosObj = new JsCOS({
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

    static getAuthorizationKey(cosAuthRO: CosAuthRO) {
        const credentials = cosAuthRO && cosAuthRO.credentials
        if (!cosAuthRO || !credentials) return console.error('credentials invalid')
        //@ts-ignore
        const authKey: string = new COS().getAuthorization({
            /** 计算签名用的密钥 SecretId，必选 */
            SecretId: cosAuthRO.credentials.tmpSecretId,
            /** 计算签名用的密钥 SecretKey，必选 */
            SecretKey: cosAuthRO.credentials.tmpSecretKey
        })
        return authKey
    }
}
