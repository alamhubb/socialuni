import SocialuniMineUserRO from "@socialuni/socialuni-api-base/src/model/user/SocialuniMineUserRO";
import StorageUtil from "@socialuni/socialuni-native-uni/src/util/StorageUtil";
import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";

export default class SocialuniUserStorageUtil {
    // 开发生产区分user，避免混淆，不区分的话会冲突
    private static readonly user_key: string = 'user_key'

    static set(user: SocialuniMineUserRO) {
        if (user) {
            SocialuniAppUtil.StorageUtil.setObj(this.user_key, user)
        } else {
            this.remove()
        }
    }

    static get(): SocialuniMineUserRO {
        // 开发环境方便测试
        return SocialuniAppUtil.StorageUtil.getObj(this.user_key)
    }

    static remove() {
        SocialuniAppUtil.StorageUtil.remove(this.user_key)
    }
}
