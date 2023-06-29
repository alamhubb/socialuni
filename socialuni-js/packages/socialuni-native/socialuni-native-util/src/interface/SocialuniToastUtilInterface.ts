import SocialuniAppUtil from "../util/SocialuniAppUtil";

export interface SocialuniToastUtilInterface {
    //交互
    toast(title: string, time?: number)

    toastLong(title: string)

    error(title: string)

    info(title: string)

    throwError(title: string)

    success(title: string)

    warn(title: string)

}
