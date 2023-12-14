import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import {socialuniUserModule} from "../store/SocialuniUserModule";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import UserPageUtil from "./UserPageUtil";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import SocialuniUserEventConst from "../constant/SocialuniUserEventConst";
import CommonEventUtil from "qingjs/src/util/CommonEventUtil";

export default class UserMsgUtil {
    static unBindPhoneNum() {
        const user: CenterUserDetailRO = socialuniUserModule.mineUser
        if (!user) {
            UserMsgUtil.unLoginMessage()
        } else {
            // 如果登录了仅仅没绑定手机号，则提示跳转，区分qq和微信不同
            QingAppUtil.AlertUtil.confirm('绑定手机号才能发布内容，是否前往绑定手机号页面')
                .then(() => {
                    UserPageUtil.toPhonePage()
                })
        }
    }

    static unLoginMessage() {
        const user = socialuniUserModule.mineUser
        if (!user) {
            QingAppUtil.AlertUtil.confirm(socialuniConfigModule.appMoreConfig.errorMsg601UnLogin).then(() => {
                CommonEventUtil.emit(SocialuniUserEventConst.toLogin)
            })
            throw new Error('未登录')
        }
        return user
    }
}
