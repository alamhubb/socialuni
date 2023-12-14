import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import UserMsgUtil from "socialuni-user-sdk/src/util/UserMsgUtil";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import SocialuniExpandAPI from "socialuni-expand-api/src/api/SocialuniExpandAPI";

export default class SocialuniUserExpandService{
    static async getOpenContactInfo(user: CenterUserDetailRO) {
        if (!socialuniUserModule.mineUser) {
            UserMsgUtil.unLoginMessage()
        }
        if (user.openContactInfo) {
            QingAppUtil.ToastUtil.error("已成功获取，无需再次获取")
        }
        const userShell = socialuniUserModule.mineUser.socialCoin
        const getUserInfoNeedCoin = socialuniConfigModule.appMoreConfig.contactExpenseShell

        if (userShell >= getUserInfoNeedCoin) {
            await QingAppUtil.AlertUtil.confirm('是否消耗100个贝壳查看用户：' + user.nickname + ' 的联系方式')
            const res = await SocialuniExpandAPI.getUserContactInfoAPI(user.id)
            user.contactInfo = res.data
            user.openContactInfo = true
            socialuniUserModule.mineUser.socialCoin = userShell - getUserInfoNeedCoin
        } else {
            await QingAppUtil.AlertUtil.confirm('您没有贝壳了，是否直接使用现金支付')
            // await PlatformUtils.payCoin(getUserInfoNeedCoin / 100)
            socialuniUserModule.mineUser.socialCoin = userShell + getUserInfoNeedCoin
            //递归调用自己
            await this.getOpenContactInfo(user)
        }
    }
}
