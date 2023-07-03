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
        ToastUtil.toast(title)
        throw new Error(title)
    }

    public static info(title: string) {
        ToastUtil.toast(title)
    }

    public static success(title: string) {
        ToastUtil.toast(title)
    }

    public static warning(title: string) {
        ToastUtil.toast(title)
    }

    public static throwError(title: string) {
        ToastUtil.toast(title)
        throw new Error(title)
    }
}
