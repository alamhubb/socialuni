import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import UserPageUtil from "./UserPageUtil";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import UserMsgUtil from "./UserMsgUtil";
import {socialuniAppUserModule} from "../store/SocialuniAppUserModule";

export default class UserCheckUtil {
    static checkUserBindPhoneNum() {
        const user = UserMsgUtil.unLoginMessage()
        console.log(user)
        if (!user || !socialuniAppUserModule.hasPhoneNum) {
            // 如果登录了仅仅没绑定手机号，则提示跳转，区分qq和微信不同
            QingAppUtil.AlertUtil.confirm('绑定手机号才能发布内容，是否前往绑定手机号页面')
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
            QingAppUtil.AlertUtil.confirm('设置学校名称才能发表内容，是否前往设置学校名称页面')
                .then(() => {
                    UserPageUtil.toMinePage()
                })
            Error('未绑定校园名称')
        }
    }

}
