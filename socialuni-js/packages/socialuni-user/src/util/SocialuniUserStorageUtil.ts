import SocialuniMineUserRO from "socialuni-api-base/src/model/user/SocialuniMineUserRO";

export default class SocialuniUserStorageUtil {
    // 开发生产区分user，避免混淆，不区分的话会冲突
    private static readonly user_key: string = 'user_key'

    static set(user: SocialuniMineUserRO) {
        if (user) {
            StorageUtil.setObj(this.user_key, user)
        } else {
            this.remove()
        }
    }

    static get(): SocialuniMineUserRO {
        // 开发环境方便测试
        return StorageUtil.getObj(this.user_key)
    }

    static remove() {
        StorageUtil.remove(this.user_key)
    }
}
