import RouterUtil from "qing-compat-js-h5/src/util/RouterUtil";
import {SocialuniUserRouter} from "socialuni-user-sdk/src/interface/SocialuniUserRouter";

class SocialuniUserRouterH5 implements SocialuniUserRouter {
    toUserDetail(userId: string) {
        RouterUtil.navigateTo('/userDetail?userId=' + userId)
    }
}

const socialuniUserRouterH5 = new SocialuniUserRouterH5()

export default socialuniUserRouterH5
