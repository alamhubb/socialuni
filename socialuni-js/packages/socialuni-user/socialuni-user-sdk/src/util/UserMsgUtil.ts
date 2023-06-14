import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import {socialuniUserModule} from "../store/SocialuniUserModule";
import PageUtil from "socialuni-util/src/util/PageUtil";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import UserPageUtil from "./UserPageUtil";

export default class UserMsgUtil {
    static unBindPhoneNum() {
        const user: CenterUserDetailRO = socialuniUserModule.mineUser
        if (!user) {
            UserMsgUtil.unLoginMessage()
        } else {
            // 如果登录了仅仅没绑定手机号，则提示跳转，区分qq和微信不同
            SocialuniAppUtil.AlertUtil.confirm('绑定手机号才能发布内容，是否前往绑定手机号页面')
                .then(() => {
                    UserPageUtil.toPhonePage()
                })
        }
    }

    static unLoginMessage() {
        if (!socialuniUserModule.mineUser) {
            SocialuniAppUtil.AlertUtil.info(socialuniConfigModule.appMoreConfig.errorMsg601UnLogin)
                .then(() => {
                    // 没token才执行登录,有token证明已经登录，如果有错误应该清空token在执行这个
                    UserPageUtil.toMinePage()
                })
            throw new Error('未登录')
        }
    }
}
