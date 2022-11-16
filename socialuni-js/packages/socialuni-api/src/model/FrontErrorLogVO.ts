import PlatformType from "socialuni-constant/constant/PlatformType";
import RouterUtil from "socialuni-sdk/src/utils/RouterUtil";
import {socialSystemModule} from "socialuni-sdk/src/store/store";

export default class FrontErrorLogVO {
    uri: string = null
    detail: string = null
    params: string = null
    errorMsg: string = null
    platform: string = null
    provider: string = null
    frontPage: string = null
    appVersion: number = null

    constructor(uri: string, detail: string, params: string, errorMsg: string) {
        this.uri = uri
        this.detail = detail
        this.params = params
        this.errorMsg = errorMsg
        this.frontPage = RouterUtil.getCurrentPageURI()
        this.platform = socialSystemModule.platform
        if (this.platform === PlatformType.mp) {
            this.provider = socialSystemModule.mpPlatform
        } else {
            this.appVersion = socialSystemModule.appVersion
        }
    }
}
