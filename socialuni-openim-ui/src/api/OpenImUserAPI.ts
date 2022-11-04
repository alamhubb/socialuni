import request from "@/plugins/request/request";
import {openImUserModule} from "@/store/store";

export default class OpenImUserAPI {
    static app_login(data) {
        const d = {
            ...data, //phoneNumber,password
            platform: openImUserModule.platform,
            operationID: openImUserModule.operationID,
        };
        // console.log(d);
        return request.post("/demo/login", d);
    }
    static app_sendCode(data) {
        return request.post("/demo/code", data);
    }
    static app_verifyCode(data) {
        return request.post("/demo/verify", data);
    }
    static app_register(data) {
        return request.post("/demo/password", data);
    }
    static app_resetPassword(data) {
        return request.post("/demo/reset_password", data);
    }
}
