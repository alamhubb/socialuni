import UniUtil from '../plugins/uni/UniUtil'
import AppConfig from '@/config/AppConfig'
import UserPayResultVO from '@/model/user/UserPayResultVO'

export default class QQUtils {
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
    return UniUtil.createRewardedVideoAd(AppConfig.qq_award_ad_id)
  }

  static createInterstitialAd () {
    return qq.createInterstitialAd({
      adUnitId: AppConfig.qq_insert_ad_id
    })
  }

  static requestPayment (payResult: UserPayResultVO): Promise<void> {
    return new Promise((resolve, reject) => {
      qq.requestPayment({
        package: payResult.package,
        bargainor_id: AppConfig.qq_bargainor_id,
        success () {
          resolve(null)
        },
        fail (err) {
          reject(err)
        }
      })
    })
  }
}
