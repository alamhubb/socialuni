import {socialuniUserModule} from "../store/SocialuniUserModule";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import SocialuniUserEventEmit from "../event/SocialuniUserEventEmit";
import SocialLoginRO from "socialuni-api-base/src/model/social/SocialLoginRO";
import SocialuniUserRO from "socialuni-api-base/src/model/user/SocialuniUserRO";

export default class SocialuniUserService {
    static loginSuccess(userRO: SocialLoginRO<SocialuniUserRO>){
        socialuniUserModule.setUserAndToken(userRO)
        SocialuniUserEventEmit.loginSuccess()
    }
    static async loginOut() {
        await QingAppUtil.AlertUtil.confirm('是否退出登录')
        socialuniUserModule.removeUserAndToken()
        QingAppUtil.ToastUtil.toast('用户退出')
        SocialuniUserEventEmit.loginOut()
    }
}
