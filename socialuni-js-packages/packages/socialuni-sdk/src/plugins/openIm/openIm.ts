import {OpenIMSDK} from "open-im-sdk";
import {InitConfig, SendMsgParams} from "open-im-sdk/types";
import {socialUserModule} from "../../store/store";
import OpenImPlatformType from "./OpenImPlatformType";

let openIm: OpenIMSDK = null;
// #ifdef APP-PLUS
// import app_im from "./app";
// openIm = app_im;
// #endif
// #ifdef H5
openIm = new OpenIMSDK();
// #endif
export default openIm;

export function openImLogin() {
    const config: InitConfig = {
        userID: socialUserModule.userId,
        token: socialUserModule.imToken,
        url: import.meta.env.VITE_APP_OPEN_IM_JS_IM_URL,
        platformID: OpenImPlatformType.web
    };
    return openIm.login(config)

}


