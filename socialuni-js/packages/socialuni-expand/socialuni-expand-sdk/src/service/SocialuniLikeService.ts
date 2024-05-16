import {socialuniAppUserModule} from "socialuni-user-sdk/src/store/SocialuniAppUserModule";
import {socialuniLikeConfigModule} from "../store/SocialuniLikeConfigModule";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import PlatformUtils from "socialuni-user-sdk/src/util/PlatformUtils";

export default class SocialuniLikeService {
    static async checkUserCoinAndPay(){
        if (socialuniAppUserModule.userCoinNum < socialuniLikeConfigModule.config.sendLikeMsgNeedPayCoinNum) {
            await QingAppUtil.AlertUtil.confirm(`打招呼需要支付${socialuniLikeConfigModule.config.sendLikeMsgNeedPayCoinNum}金币，您的金币不足，余额为：${socialuniAppUserModule.userCoinNum},是否充值金币`)
            await PlatformUtils.payCoin(1)
            socialuniAppUserModule.userCoinNum = socialuniAppUserModule.userCoinNum + 100
        }
    }
}
