import AppConfig from '../config/AppConfig'
import RouterUtil from '../utils/RouterUtil'

export default class SkipUrlConst {
  static homeUrl (): string {
    return RouterUtil.getWebUrl(socialConfigModule.homeUrl, AppConfig.title)
  }

  static suggestUrl (): string {
    return RouterUtil.getWebUrl(socialConfigModule.suggestUrl, AppConfig.suggestTitle)
  }

  static contactUsUrl (): string {
    return RouterUtil.getWebUrl(socialConfigModule.contactUsUrl, AppConfig.contactTitle)
  }

  static userAgreementUrl (): string {
    return RouterUtil.getWebUrl(socialConfigModule.userAgreementUrl, AppConfig.userAgreementTitle)
  }

  static userPrivacyUrl (): string {
    return RouterUtil.getWebUrl(socialConfigModule.userPrivacyUrl, AppConfig.userPrivacyTitle)
  }

  static childProtectUrl (): string {
    return RouterUtil.getWebUrl(socialConfigModule.childProtectUrl, AppConfig.childProtectTitle)
  }

  static shellInfoUrl (): string {
    return RouterUtil.getWebUrl(socialConfigModule.childProtectUrl, AppConfig.shellInfoTitle)
    // return RouterUtil.getWebUrl(ConfigMap.shellInfoUrlDefault, AppConfig.shellInfoTitle)
  }
}
