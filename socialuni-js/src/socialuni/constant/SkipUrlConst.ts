import AppConfig from '../config/AppConfig'
import {socialConfigModule} from '../store'
import RouterUtil from '../utils/RouterUtil'

export default class SkipUrlConst {
  static homeUrl(): string {
    return RouterUtil.getWebUrl(socialConfigModule.appConfig.homeUrl, AppConfig.title)
  }

  static suggestUrl(): string {
    return RouterUtil.getWebUrl(socialConfigModule.appConfig.suggestUrl, AppConfig.suggestTitle)
  }

  static contactUsUrl(): string {
    return RouterUtil.getWebUrl(socialConfigModule.appConfig.contactUsUrl, AppConfig.contactTitle)
  }

  static userAgreementUrl(): string {
    return RouterUtil.getWebUrl(socialConfigModule.appConfig.userAgreementUrl, AppConfig.userAgreementTitle)
  }

  static userPrivacyUrl(): string {
    return RouterUtil.getWebUrl(socialConfigModule.appConfig.userPrivacyUrl, AppConfig.userPrivacyTitle)
  }

  static childProtectUrl(): string {
    return RouterUtil.getWebUrl(socialConfigModule.appConfig.childProtectUrl, AppConfig.childProtectTitle)
  }
}
