import AppConfig from "./AppConfig";
// import {socialuniConfigModule} from "socialuni-sdk/src/store/store";
import PageUtil from "socialuni-util/src/util/PageUtil";
import {socialuniConfigModule} from "socialuni-base-api/src/store/SocialuniConfigModule";

export default class SkipUrlConst {
  static homeUrl(): string {
    return PageUtil.getWebUrl(socialuniConfigModule.appMoreConfig.homeUrl, AppConfig.title)
  }

  static suggestUrl(): string {
    return PageUtil.getWebUrl(socialuniConfigModule.appMoreConfig.suggestUrl, AppConfig.suggestTitle)
  }

  static contactUsUrl(): string {
    return PageUtil.getWebUrl(socialuniConfigModule.appMoreConfig.contactUsUrl, AppConfig.contactTitle)
  }

  static userAgreementUrl(): string {
    return PageUtil.getWebUrl(socialuniConfigModule.appMoreConfig.userAgreementUrl, AppConfig.userAgreementTitle)
  }

  static userPrivacyUrl(): string {
    return PageUtil.getWebUrl(socialuniConfigModule.appMoreConfig.userPrivacyUrl, AppConfig.userPrivacyTitle)
  }

  static childProtectUrl(): string {
    return PageUtil.getWebUrl(socialuniConfigModule.appMoreConfig.childProtectUrl, AppConfig.childProtectTitle)
  }
}
