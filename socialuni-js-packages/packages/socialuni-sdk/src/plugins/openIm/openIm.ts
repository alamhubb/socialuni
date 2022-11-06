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

export function initOpenIm() {
    const config: InitConfig = {
        userID: socialUserModule.userId,
        token: socialUserModule.imToken,
        url: import.meta.env.VITE_APP_OPEN_IM_JS_IM_URL,
        platformID: OpenImPlatformType.web
    };
    openIm
        .login(config)
        .then(async (res) => {
            console.log("login suc...");
            const textStr = "hello open im";
            console.log('发送消息')
            const {data} = await openIm.createTextMessage(textStr);
            const params = {
                recvID: "f400ccbf06b642a4abf9c589876566ec",
                groupID: "",
                message: data,
            };
            openIm.sendMessage(params).then(res => {
                console.log('发送消息成功')
            });
        })
        .catch((err) => {
            console.log("login failed...");
        })
        .finally(() => {
            console.log(456456)
        });
}


