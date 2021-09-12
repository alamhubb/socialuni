import DevModeType from './DevModeType'

export default class SocialSystemInfo {
  static readonly isDevMode: boolean = process.env.NODE_ENV === DevModeType.dev
}
