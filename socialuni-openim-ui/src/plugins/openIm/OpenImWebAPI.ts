import {OpenImAPI} from "@/plugins/openIm/OpenImAPI";
import {OpenIMSDK} from "open-im-sdk";
import {initOpenImStore, openImUserModule} from "@/plugins/openIm/store/store";
import {InitConfig} from "open-im-sdk/types";

const openIM = new OpenIMSDK();

export default class OpenImWebAPI implements OpenImAPI {
    //vite插件初始化方法
    install() {
        initOpenImStore()
        this.init()
    }

    //初始化platform
    private init_platform() {
        //都是业务代码
        const systemInfo = uni.getSystemInfoSync();
        openImUserModule.set_systemInfo(systemInfo)
        openImUserModule.set_operationID(systemInfo.deviceId)
        openImUserModule.set_platform(5)
    }

    init() {
        this.init_platform();
        const config: InitConfig = {
            userID: openImUserModule.userID,
            token: openImUserModule.token,
            url: import.meta.env.VITE_APP_OPEN_IM_JS_IM_URL,
            platformID: openImUserModule.platform,
        };
        openIM
            .login(config)
            .then((res) => {
                console.log("login suc...");
            })
            .catch((err) => {
                console.log("login failed...");
            });
    }

    login() {
    }

}
