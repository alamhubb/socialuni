import QingAppUtil from "qingjs/src/util/QingAppUtil";

export default class SocialuniImUserTokenUtil {
    // 开发生产区分token，避免混淆，不区分的话会冲突
    private static readonly socialuniUserImTokenKey: string = 'socialuni_im_user_token'

    static set(token: string) {
        if (token) {
            QingAppUtil.StorageUtil.set(this.socialuniUserImTokenKey, token)
        } else {
            this.remove()
        }
    }

    static get(): string {
        return QingAppUtil.StorageUtil.get(this.socialuniUserImTokenKey)
    }

    static remove() {
        QingAppUtil.StorageUtil.remove(this.socialuniUserImTokenKey)
    }
}
