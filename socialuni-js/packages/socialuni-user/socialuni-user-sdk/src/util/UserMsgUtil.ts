import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import {socialuniUserModule} from "../store/SocialuniUserModule";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import UserPageUtil from "./UserPageUtil";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import SocialuniUserEventConst from "../constant/SocialuniUserEventConst";
import CommonEventUtil from "qingjs/src/util/CommonEventUtil";

export default class UserMsgUtil {
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
