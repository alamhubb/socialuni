import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import AlertUtil from "socialuni-util/src/util/AlertUtil";
import PageUtil from "socialuni-util/src/util/PageUtil";
import {socialuniConfigModule} from "socialuni/src/store/SocialuniConfigModule";
import {socialuniUserModule} from "socialuni/src/store/SocialuniUserModule";
import UserPageUtil from "socialuni/src/util/UserPageUtil";

export default class UserCheckUtil {
    static checkUserLogin() {
        const user = socialuniUserModule.mineUser
        if (!user) {
            AlertUtil.confirm(socialuniConfigModule.appMoreConfig.errorMsg601UnLogin)
            Error('未登录')
        }
        return user
    }

    static checkUserBindPhoneNum() {
        const user = this.checkUserLogin()
        if (!user.phoneNum) {
            // 如果登录了仅仅没绑定手机号，则提示跳转，区分qq和微信不同
            AlertUtil.confirm('绑定手机号才能发布内容，是否前往绑定手机号页面')
                .then(() => {
                    UserPageUtil.toPhonePage()
                })
            Error('未绑定手机号')
        }
        return user
    }


    static checkUserBindSchool() {
        const user = this.checkUserBindPhoneNum()
        if (!user.schoolName && socialuniConfigModule.appConfig.mustSetSchoolCanPost) {
            // 如果登录了仅仅没绑定手机号，则提示跳转，区分qq和微信不同
            AlertUtil.confirm('设置学校名称才能发表内容，是否前往设置学校名称页面')
                .then(() => {
                    UserPageUtil.toMinePage()
                })
            Error('未绑定校园名称')
        }
    }

}
