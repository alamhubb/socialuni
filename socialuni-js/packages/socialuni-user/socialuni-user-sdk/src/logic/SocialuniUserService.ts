import {socialuniUserModule} from "../store/SocialuniUserModule";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import SocialuniUserEventEmit from "../event/SocialuniUserEventEmit";
import SocialLoginRO from "socialuni-api-base/src/model/social/SocialLoginRO";
import SocialuniUserRO from "socialuni-api-base/src/model/user/SocialuniUserRO";

export default class SocialuniUserService {
    static loginSuccess(userRO: SocialLoginRO<SocialuniUserRO>) {
        QingAppUtil.ToastUtil.success('登录成功')
        socialuniUserModule.setUserAndToken(userRO)
        SocialuniUserEventEmit.loginSuccess()
    }

    static async loginOut() {
        await QingAppUtil.AlertUtil.confirm('是否退出登录')
        QingAppUtil.ToastUtil.toast('用户退出')
        this.clearUserInfo()
    }

    static clearUserInfo() {
        socialuniUserModule.removeUserAndToken()
        SocialuniUserEventEmit.loginOut()
    }
}
