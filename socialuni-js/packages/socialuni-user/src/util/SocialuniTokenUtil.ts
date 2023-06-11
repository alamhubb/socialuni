import StorageUtil from "socialuni-native-uni/src/util/StorageUtil";

export default class SocialuniTokenUtil {
    // 开发生产区分token，避免混淆，不区分的话会冲突
    private static readonly socialuniUserTokenKey: string = 'token'

    static set(token: string) {
        if (token) {
            StorageUtil.set(this.socialuniUserTokenKey, token)
        } else {
            this.remove()
        }
    }

    static get(): string {
        return StorageUtil.get(this.socialuniUserTokenKey)
    }

    static remove() {
        StorageUtil.remove(this.socialuniUserTokenKey)
    }
}
