import PlatformType from "socialuni-constant/constant/PlatformType";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";

export default class FrontErrorLogVO {
    uri: string = null
    detail: string = null
    params: string = null
    errorMsg: string = null
    platform: string = null
    provider: string = null
    frontPage: string = null
    appVersion: number = null

    constructor(uri: string, detail: string, params: string, errorMsg: string, frontPage: string) {
        this.uri = uri
        this.detail = detail
        this.params = params
        this.errorMsg = errorMsg
        this.platform = socialuniSystemModule.platform
        if (this.platform === PlatformType.mp) {
            this.provider = socialuniSystemModule.provider
        } else {
            this.appVersion = socialuniSystemModule.appVersion
        }
    }
}
