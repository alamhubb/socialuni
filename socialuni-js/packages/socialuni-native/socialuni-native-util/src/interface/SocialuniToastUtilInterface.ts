export interface SocialuniToastUtilInterface {
    //交互
    toast(title: string, time?: number)

    toastLong(title: string)

    error(title: string)

    throwError(title: string)
}
