import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import PlatformUtils from "../util/PlatformUtils";
import SocialuniCoinAPI from "socialuni-user-api/src/api/SocialuniCoinAPI";

export default class SocialuniPay{
    //所有只能直接调用这个
    static async payCoin(amount: number) {
        PlatformUtils.checkPay()
        //目前支持微信支付
        // const provider: string = SocialuniProviderType.wx
        return SocialuniCoinAPI.payCoinAPI(socialuniSystemModule.mpPlatform, amount).then((res) => {
            return PlatformUtils.cashPay(res.data)
        })
    }
}
