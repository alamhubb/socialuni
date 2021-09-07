import UniUtil from './UniUtil'
import AppConfig from '/socialuni/config/AppConfig'
import UserPayResultVO from '/socialuni/model/user/UserPayResultVO'
import PlatformType from '/socialuni/const/PlatformType'
import { socialSystemModule } from '/socialuni/store'

export default class WxUtils {
  static subscribeAppMsg (tmplIds: string[]) {
    return new Promise((resolve, reject) => {
      qq.subscribeAppMsg({
        tmplIds: tmplIds,
        subscribe: true,
        success () {
          resolve(null)
        },
        fail (err) {
          reject(err)
        }
      })
    })
  }

  static createRewardedVideoAd () {
    return UniUtil.createRewardedVideoAd(AppConfig.wx_award_ad_id)
  }

  static createInterstitialAd () {
    return qq.createInterstitialAd({
      adUnitId: AppConfig.qq_insert_ad_id
    })
  }

  static requestPayment (payResult: UserPayResultVO): Promise<void> {
    return new Promise((resolve, reject) => {
      if (PlatformType.mp === socialSystemModule.platform) {
        wx.requestPayment({
          timeStamp: payResult.timeStamp,
          nonceStr: payResult.nonceStr,
          package: payResult.package,
          signType: 'MD5',
          paySign: payResult.paySign,
          success () {
            resolve(null)
          },
          fail (err) {
            reject(err)
          }
        })
      } else {
        const orderInfo = {
          appid: AppConfig.wx_app_id,
          partnerid: AppConfig.wx_partnerid_id,
          prepayid: payResult.prepayid,
          package: payResult.package,
          noncestr: payResult.nonceStr,
          timestamp: payResult.timeStamp,
          sign: payResult.paySign
        }
        uni.requestPayment({
          provider: 'wxpay',
          //eslint-disable-next-line
          //@ts-ignore
          orderInfo: orderInfo,
          success () {
            resolve(null)
          },
          fail (err) {
            reject(err)
          }
        })
      }
    })
  }
}
