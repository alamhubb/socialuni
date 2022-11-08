import AppConfig from "./AppConfig";
import {socialConfigModule} from "socialuni-sdk/src/store/store";
import PageUtil from "socialuni-common/src/utils/PageUtil";

export default class SkipUrlConst {
  static homeUrl(): string {
    return PageUtil.getWebUrl(socialConfigModule.appMoreConfig.homeUrl, AppConfig.title)
  }

  static suggestUrl(): string {
    return PageUtil.getWebUrl(socialConfigModule.appMoreConfig.suggestUrl, AppConfig.suggestTitle)
  }

  static contactUsUrl(): string {
    return PageUtil.getWebUrl(socialConfigModule.appMoreConfig.contactUsUrl, AppConfig.contactTitle)
  }

  static userAgreementUrl(): string {
    return PageUtil.getWebUrl(socialConfigModule.appMoreConfig.userAgreementUrl, AppConfig.userAgreementTitle)
  }

  static userPrivacyUrl(): string {
    return PageUtil.getWebUrl(socialConfigModule.appMoreConfig.userPrivacyUrl, AppConfig.userPrivacyTitle)
  }

  static childProtectUrl(): string {
    return PageUtil.getWebUrl(socialConfigModule.appMoreConfig.childProtectUrl, AppConfig.childProtectTitle)
  }
}
