export default class SocialServerConfig {
  static readonly SOCIAL_APP_SERVER_URL: string = process.env.VUE_APP_SOCAIL_APP_SERVER_URL
  static readonly SOCIAL_UNI_SERVER_URL: string = process.env.VUE_APP_SOCAIL_UNI_SERVER_URL

  // static readonly APP_SERVER_OPTIONS: options = { baseUrl: SocialServerConfig.SOCIAL_APP_SERVER_URL }
  // static readonly SOCIAL_UNI_SERVER_URL: options = { baseUrl: SocialServerConfig.SOCIAL_UNI_SERVER_URL }
}
