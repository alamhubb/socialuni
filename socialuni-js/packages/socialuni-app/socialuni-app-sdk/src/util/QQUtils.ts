import AppConfig from "@socialuni/socialuni-constant/constant/AppConfig";
import UserPayResultVO from "@socialuni/socialuni-api-base/src/model/user/UserPayResultVO";
import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";

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
    return SocialuniAppUtil.NativeUtil.createRewardedVideoAd(AppConfig.qq_award_ad_id)
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
