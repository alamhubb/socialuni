import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";

export default class SocialuniTokenUtil {
    // 开发生产区分token，避免混淆，不区分的话会冲突
    private static readonly socialuniUserTokenKey: string = 'token'

    static set(token: string) {
        if (token) {
            QingAppUtil.StorageUtil.set(this.socialuniUserTokenKey, token)
        } else {
            this.remove()
        }
    }

    static get(): string {
        return QingAppUtil.StorageUtil.get(this.socialuniUserTokenKey)
    }

    static remove() {
        QingAppUtil.StorageUtil.remove(this.socialuniUserTokenKey)
    }
}
