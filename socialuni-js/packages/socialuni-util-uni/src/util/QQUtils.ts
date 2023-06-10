import UniUtil from './UniUtil'
import AppConfig from "socialuni-constant/constant/AppConfig";
import UserPayResultVO from "socialuni-api-base/src/model/user/UserPayResultVO";

export default class QQUtils {
  static subscribeAppMsg (tmplIds: string[]) {
    return new Promise((resolve, reject) => {
      //@ts-ignore
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
    //@ts-ignore
    return qq.createInterstitialAd({
      adUnitId: AppConfig.qq_insert_ad_id
    })
  }

  static requestPayment (payResult: UserPayResultVO): Promise<void> {
    return new Promise((resolve, reject) => {
      //@ts-ignore
      qq.requestWxPayment({
        url: payResult.mweb_url,
        referer: "https://www.qq.com",
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
