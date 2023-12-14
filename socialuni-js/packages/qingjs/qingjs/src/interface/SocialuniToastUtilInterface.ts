import QingAppUtil from "../util/QingAppUtil";

export interface SocialuniToastUtilInterface {
    //交互
    toast(title: string, time?: number)

    toastLong(title: string)

    error(title: string)

    info(title: string)

    throwError(title: string)

    success(title: string)

    warning(title: string)

}
