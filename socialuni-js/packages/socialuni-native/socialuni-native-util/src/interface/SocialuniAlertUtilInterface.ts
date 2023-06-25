export interface SocialuniAlertUtilInterface {
    confirm(msg: string, okLabel?: string, cancel?: string)

    hint(msg: string, okLabel?: string)

    info(msg: string, okLabel?: string)

    warning(msg: string, okLabel?: string)

    success(msg: string, okLabel?: string)

    error(msg: string, okLabel?: string)
}
