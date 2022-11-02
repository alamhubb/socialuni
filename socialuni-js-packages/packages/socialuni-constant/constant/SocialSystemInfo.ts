import DevModeType from './DevModeType'

export default class SocialSystemInfo {
    static readonly isDevMode: boolean = import.meta.env.DEV
}
