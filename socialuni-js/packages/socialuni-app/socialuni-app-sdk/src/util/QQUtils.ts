import AppConfig from "socialuni-constant/constant/AppConfig";
import UserPayResultVO from "socialuni-api-base/src/model/user/UserPayResultVO";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";

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
    return QingAppUtil.NativeUtil.createRewardedVideoAd(AppConfig.qq_award_ad_id)
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
