import {socialuniAppUserModule} from "socialuni-user-sdk/src/store/SocialuniAppUserModule";
import {socialuniLikeConfigModule} from "../store/SocialuniLikeConfigModule";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import PlatformUtils from "socialuni-user-sdk/src/util/PlatformUtils";
import SocialuniAppConfig from "socialuni-app-sdk/src/constant/SocialuniAppConfig";
import UserMsgUtil from "socialuni-user-sdk/src/util/UserMsgUtil";

export default class SocialuniLikeService {
    static async checkUserCoinAndPay(coinNum: number) {
        const defaultPayNum = 1
        UserMsgUtil.unLoginMessage()
        if (socialuniAppUserModule.userCoinNum < coinNum) {
            await QingAppUtil.AlertUtil.confirm(`打招呼需要支付${coinNum}金币，您的金币不足，余额为：${socialuniAppUserModule.userCoinNum},是否充值金币`)
            await PlatformUtils.payCoin(defaultPayNum)
            socialuniAppUserModule.userCoinNum = socialuniAppUserModule.userCoinNum + (defaultPayNum * SocialuniAppConfig.coinRatio)
        }
    }
}
