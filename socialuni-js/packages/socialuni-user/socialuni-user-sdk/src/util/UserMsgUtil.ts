import CenterUserDetailRO from "@socialuni/socialuni-api-base/src/model/social/CenterUserDetailRO";
import {socialuniUserModule} from "../store/SocialuniUserModule";
import {socialuniConfigModule} from "@socialuni/socialuni-app-sdk/src/store/SocialuniConfigModule";
import UserPageUtil from "./UserPageUtil";
import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";
import SocialuniUserEventConst from "../constant/SocialuniUserEventConst";
import CommonEventUtil from "@socialuni/socialuni-native-util/src/util/CommonEventUtil";

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
        const user = socialuniUserModule.mineUser
        if (!user) {
            SocialuniAppUtil.AlertUtil.confirm(socialuniConfigModule.appMoreConfig.errorMsg601UnLogin).then(() => {
                CommonEventUtil.emit(SocialuniUserEventConst.toLogin)
            })
            throw new Error('未登录')
        }
        return user
    }
}
