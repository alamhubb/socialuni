import AppConfig from '@/const/AppConfig'
import { configModule } from '@/plugins/store'
import RouterUtil from '@/utils/RouterUtil'
import PageUtil from '@/utils/PageUtil'

export default class SkipUrlConst {
  static homeUrl (): string {
    return RouterUtil.getWebUrl(configModule.homeUrl, AppConfig.title)
  }

  static suggestUrl (): string {
    return RouterUtil.getWebUrl(configModule.suggestUrl, AppConfig.suggestTitle)
  }

  static contactUsUrl (): string {
    return RouterUtil.getWebUrl(configModule.contactUsUrl, AppConfig.contactTitle)
  }

  static userAgreementUrl (): string {
    return RouterUtil.getWebUrl(configModule.userAgreementUrl, AppConfig.userAgreementTitle)
  }

  static userPrivacyUrl (): string {
    return RouterUtil.getWebUrl(configModule.userPrivacyUrl, AppConfig.userPrivacyTitle)
  }

  static childProtectUrl (): string {
    return RouterUtil.getWebUrl(configModule.childProtectUrl, AppConfig.childProtectTitle)
  }

  static shellInfoUrl (): string {
    return RouterUtil.getWebUrl(configModule.childProtectUrl, AppConfig.shellInfoTitle)
    // return RouterUtil.getWebUrl(ConfigMap.shellInfoUrlDefault, AppConfig.shellInfoTitle)
  }
}
