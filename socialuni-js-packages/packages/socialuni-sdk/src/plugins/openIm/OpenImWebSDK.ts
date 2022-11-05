import {OpenIMSDK} from "open-im-sdk";
import {InitConfig} from "open-im-sdk/types";
import {OpenImAPI} from "./OpenImAPI";
import {socialUserModule} from "../../store/store";
import OpenImPlatformType from "./OpenImPlatformType";

const openIM = new OpenIMSDK();

export default class OpenImWebSDK implements OpenImAPI {
    init() {
        const config: InitConfig = {
            userID: socialUserModule.userId,
            token: socialUserModule.token,
            url: import.meta.env.VITE_APP_OPEN_IM_JS_IM_URL,
            platformID: OpenImPlatformType.web
        };
        openIM
            .login(config)
            .then((res) => {
                console.log("login suc...");
            })
            .catch((err) => {
                console.log("login failed...");
            })
            .finally(() => {
                console.log(456456)
            });
    }

    login() {
    }

}
