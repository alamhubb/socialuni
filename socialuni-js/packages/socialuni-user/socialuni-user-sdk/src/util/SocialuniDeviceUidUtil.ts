import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import SocialuniAppConst from "socialuni-app-sdk/src/constant/SocialuniAppConst";

export default class SocialuniDeviceUidUtil {
    static set(token: string) {
        if (token) {
            QingAppUtil.StorageUtil.set(SocialuniAppConst.deviceUidKey, token)
        } else {
            this.remove()
        }
    }

    static get(): string {
        return QingAppUtil.StorageUtil.get(SocialuniAppConst.deviceUidKey)
    }

    static remove() {
        QingAppUtil.StorageUtil.remove(SocialuniAppConst.deviceUidKey)
    }
}
