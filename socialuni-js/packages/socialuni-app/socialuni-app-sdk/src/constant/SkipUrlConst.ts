import {socialuniConfigModule} from "socialuni-app/src/store/SocialuniConfigModule";
import AppConfig from "socialuni-constant/constant/AppConfig";
import SocialuniAppPageUtil from "../util/SocialuniAppPageUtil";

export default class SkipUrlConst {
  static homeUrl(): string {
    return SocialuniAppPageUtil.getWebUrl(socialuniConfigModule.appMoreConfig.homeUrl, AppConfig.title)
  }

  static suggestUrl(): string {
    return SocialuniAppPageUtil.getWebUrl(socialuniConfigModule.appMoreConfig.suggestUrl, AppConfig.suggestTitle)
  }

  static contactUsUrl(): string {
    return SocialuniAppPageUtil.getWebUrl(socialuniConfigModule.appMoreConfig.contactUsUrl, AppConfig.contactTitle)
  }

  static userAgreementUrl(): string {
    return SocialuniAppPageUtil.getWebUrl(socialuniConfigModule.appMoreConfig.userAgreementUrl, AppConfig.userAgreementTitle)
  }

  static userPrivacyUrl(): string {
    return SocialuniAppPageUtil.getWebUrl(socialuniConfigModule.appMoreConfig.userPrivacyUrl, AppConfig.userPrivacyTitle)
  }

  static childProtectUrl(): string {
    return SocialuniAppPageUtil.getWebUrl(socialuniConfigModule.appMoreConfig.childProtectUrl, AppConfig.childProtectTitle)
  }
}
