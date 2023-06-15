import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import UserPageUtil from "./UserPageUtil";
import SocialuniUserEventConst from "../constant/SocialuniUserEventConst";
import SocialuniEventUtil from "socialuni/src/util/SocialuniEventUtil";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";

export default class UserCheckUtil {
    static checkUserLogin() {
        const user = socialuniUserModule.mineUser
        if (!user) {
            SocialuniAppUtil.AlertUtil.confirm(socialuniConfigModule.appMoreConfig.errorMsg601UnLogin).then(() => {
                SocialuniEventUtil.emit(SocialuniUserEventConst.toLogin)
            })
            throw Error('未登录')
        }
        return user
    }

    static checkUserBindPhoneNum() {
        const user = this.checkUserLogin()
        if (!user || !user.phoneNum) {
            // 如果登录了仅仅没绑定手机号，则提示跳转，区分qq和微信不同
            SocialuniAppUtil.AlertUtil.confirm('绑定手机号才能发布内容，是否前往绑定手机号页面')
                .then(() => {
                    UserPageUtil.toPhonePage()
                })
            throw Error('未绑定手机号')
        }
        return user
    }


    static checkUserBindSchool() {
        const user = this.checkUserBindPhoneNum()
        if (!user.schoolName && socialuniConfigModule.appConfig.mustSetSchoolCanPost) {
            // 如果登录了仅仅没绑定手机号，则提示跳转，区分qq和微信不同
            SocialuniAppUtil.AlertUtil.confirm('设置学校名称才能发表内容，是否前往设置学校名称页面')
                .then(() => {
                    UserPageUtil.toMinePage()
                })
            Error('未绑定校园名称')
        }
    }

}
