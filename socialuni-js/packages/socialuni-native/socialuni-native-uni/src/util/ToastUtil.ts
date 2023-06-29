import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";

export default class ToastUtil {
    //交互
    public static toast(title: string, time?: number) {
        return new Promise((resolve, reject) => {
            uni.showToast({
                icon: 'none',
                title: title,
                duration: time || 800,
                success() {
                    resolve(null)
                },
                fail(err) {
                    reject(err)
                }
            })
        })
    }

    public static toastLong(title: string) {
        return new Promise((resolve, reject) => {
            uni.showToast({
                icon: 'none',
                title: title,
                duration: 1500,
                success() {
                    resolve(null)
                },
                fail(err) {
                    reject(err)
                }
            })
        })
    }

    public static error(title: string) {
        SocialuniAppUtil.ToastUtil.toast(title)
        throw new Error(title)
    }

    public static info(title: string) {
        SocialuniAppUtil.ToastUtil.toast(title)
    }

    public static success(title: string) {
        SocialuniAppUtil.ToastUtil.toast(title)
    }

    public static warning(title: string) {
        SocialuniAppUtil.ToastUtil.toast(title)
    }

    public static throwError(title: string) {
        SocialuniAppUtil.ToastUtil.toast(title)
        throw new Error(title)
    }
}
