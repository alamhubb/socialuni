import AppConfig from "socialuni-constant/constant/AppConfig";
import UserPayResultVO from "socialuni-api-base/src/model/user/UserPayResultVO";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import PlatformType from "socialuni-constant/constant/PlatformType";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";

export default class WxUtils {
    static subscribeAppMsg(tmplIds: string[]) {
        return new Promise((resolve, reject) => {
            //@ts-ignore
            qq.subscribeAppMsg({
                tmplIds: tmplIds,
                subscribe: true,
                success() {
                    resolve(null)
                },
                fail(err) {
                    reject(err)
                }
            })
        })
    }

    static createRewardedVideoAd() {
        return QingAppUtil.NativeUtil.createRewardedVideoAd(AppConfig.wx_award_ad_id)
    }

    static createInterstitialAd() {
        //@ts-ignore
        return qq.createInterstitialAd({
            adUnitId: AppConfig.qq_insert_ad_id
        })
    }

    static requestPayment(payResult: UserPayResultVO): Promise<void> {
        return new Promise((resolve, reject) => {
            if (PlatformType.mp === socialuniSystemModule.platform) {
                //@ts-ignore
                wx.requestPayment({
                    timeStamp: payResult.timeStamp,
                    nonceStr: payResult.nonceStr,
                    package: payResult.package,
                    signType: 'MD5',
                    paySign: payResult.paySign,
                    success() {
                        resolve(null)
                    },
                    fail(err) {
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
                    success() {
                        resolve(null)
                    },
                    fail(err) {
                        reject(err)
                    }
                })
            }
        })
    }
}
