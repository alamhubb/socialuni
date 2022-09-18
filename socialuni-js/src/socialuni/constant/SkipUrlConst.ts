import AppConfig from '../config/AppConfig'
import {socialConfigModule} from '../store'
import RouterUtil from '../utils/RouterUtil'

export default class SkipUrlConst {
  static homeUrl(): string {
    return RouterUtil.getWebUrl(socialConfigModule.appMoreConfig.homeUrl, AppConfig.title)
  }

  static suggestUrl(): string {
    return RouterUtil.getWebUrl(socialConfigModule.appMoreConfig.suggestUrl, AppConfig.suggestTitle)
  }

  static contactUsUrl(): string {
    return RouterUtil.getWebUrl(socialConfigModule.appMoreConfig.contactUsUrl, AppConfig.contactTitle)
  }

  static userAgreementUrl(): string {
    return RouterUtil.getWebUrl(socialConfigModule.appMoreConfig.userAgreementUrl, AppConfig.userAgreementTitle)
  }

  static userPrivacyUrl(): string {
    return RouterUtil.getWebUrl(socialConfigModule.appMoreConfig.userPrivacyUrl, AppConfig.userPrivacyTitle)
  }

  static childProtectUrl(): string {
    return RouterUtil.getWebUrl(socialConfigModule.appMoreConfig.childProtectUrl, AppConfig.childProtectTitle)
  }
}
