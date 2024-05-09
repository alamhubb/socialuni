import SocialuniMineUserRO from "socialuni-api-base/src/model/user/SocialuniMineUserRO";
import StorageUtil from "qingjs-uni/src/util/StorageUtil";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import SocialuniUserRO from "socialuni-api-base/src/model/user/SocialuniUserRO";

export default class SocialuniUserStorageUtil {
    // 开发生产区分user，避免混淆，不区分的话会冲突
    private static readonly user_key: string = 'user_key'

    static set(user: SocialuniUserRO) {
        if (user) {
            QingAppUtil.StorageUtil.setObj(this.user_key, user)
        } else {
            this.remove()
        }
    }

    static get(): SocialuniMineUserRO {
        // 开发环境方便测试
        return QingAppUtil.StorageUtil.getObj(this.user_key)
    }

    static remove() {
        QingAppUtil.StorageUtil.remove(this.user_key)
    }
}
