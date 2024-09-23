import RouterUtil from "qing-compat-js-h5/src/util/RouterUtil";

@service
export default class SocialuniUserRouterUtil {
    toUserDetail(userId: string) {
        RouterUtil.navigateTo('/userDetail?userId=' + userId)
    }
}
