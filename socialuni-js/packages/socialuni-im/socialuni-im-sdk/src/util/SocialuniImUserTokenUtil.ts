import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";

export default class SocialuniImUserTokenUtil {
    // 开发生产区分token，避免混淆，不区分的话会冲突
    private static readonly socialuniUserImTokenKey: string = 'socialuni_im_user_token'

    static set(token: string) {
        if (token) {
            SocialuniAppUtil.StorageUtil.set(this.socialuniUserImTokenKey, token)
        } else {
            this.remove()
        }
    }

    static get(): string {
        return SocialuniAppUtil.StorageUtil.get(this.socialuniUserImTokenKey)
    }

    static remove() {
        SocialuniAppUtil.StorageUtil.remove(this.socialuniUserImTokenKey)
    }
}
