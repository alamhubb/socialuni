import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";

export default class SocialuniTokenUtil {
    // 开发生产区分token，避免混淆，不区分的话会冲突
    private static readonly socialuniUserTokenKey: string = 'token'

    static set(token: string) {
        if (token) {
            SocialuniAppUtil.StorageUtil.set(this.socialuniUserTokenKey, token)
        } else {
            this.remove()
        }
    }

    static get(): string {
        return SocialuniAppUtil.StorageUtil.get(this.socialuniUserTokenKey)
    }

    static remove() {
        SocialuniAppUtil.StorageUtil.remove(this.socialuniUserTokenKey)
    }
}
