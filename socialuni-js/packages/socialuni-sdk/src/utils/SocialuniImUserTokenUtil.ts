import StorageUtil from "socialuni-sdk/src/utils/StorageUtil";

export default class SocialuniImUserTokenUtil {
    // 开发生产区分token，避免混淆，不区分的话会冲突
    private static readonly socialuniUserImTokenKey: string = 'socialuni_im_user_token'

    static set(token: string) {
        if (token) {
            StorageUtil.set(this.socialuniUserImTokenKey, token)
        } else {
            this.remove()
        }
    }

    static get(): string {
        return StorageUtil.get(this.socialuniUserImTokenKey)
    }

    static remove() {
        StorageUtil.remove(this.socialuniUserImTokenKey)
    }
}
